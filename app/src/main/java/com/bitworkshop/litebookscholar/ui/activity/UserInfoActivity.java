package com.bitworkshop.litebookscholar.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.presenter.EditInfoPresenter;
import com.bitworkshop.litebookscholar.presenter.MinePresenter;
import com.bitworkshop.litebookscholar.ui.fragment.ChooseDialogFragment;
import com.bitworkshop.litebookscholar.ui.view.CircleImageView;
import com.bitworkshop.litebookscholar.ui.view.IEditUserInfoView;
import com.bitworkshop.litebookscholar.ui.view.IMineView;
import com.bitworkshop.litebookscholar.util.MyToastUtils;
import com.bitworkshop.litebookscholar.util.ProgressDialogUtil;
import com.bitworkshop.litebookscholar.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.signature.StringSignature;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity implements IMineView, IEditUserInfoView, ChooseDialogFragment.OnDialogItemClickListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_user_icon)
    CircleImageView imageUserIcon;
    @BindView(R.id.card_view_user_image)
    CardView cardViewUserImage;
    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.card_view_user_nickname)
    CardView cardViewUserNickname;

    private MinePresenter presenter;
    private String userAccount;
    private String password;
    private String imageUrl;
    private static final int REQUEST_IMAGE_CAPTURE = 1;//从相机调用
    private static final int REQUEST_IMAGE_FORM_PICK = 2;//从系统相册
    private Uri photoUri;
    private Uri postData;
    private EditInfoPresenter editInfoPresenter;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "个人信息", true);
        presenter = new MinePresenter(this);
        editInfoPresenter = new EditInfoPresenter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.card_view_user_image, R.id.card_view_user_nickname})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view_user_image:
                FragmentManager fm = getSupportFragmentManager();
                ChooseDialogFragment chooseDialogFragment = ChooseDialogFragment.getInstance();
                chooseDialogFragment.show(fm, "show_dialog");
                break;
            case R.id.card_view_user_nickname:
                startActivity(new Intent(this, UpdateNickNameActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getUserAccount() != null) {
            presenter.setUserInfo(getUserAccount());
        }
    }

    @Override
    public void setUserInfo(User user) {
        userAccount = user.getUser();
        password = user.getUserPassword();
        nickname = user.getPetname();
        if (!user.getUrl().equals("")) {
            Glide.with(this).load(user.getUrl())
                    .placeholder(R.drawable.default_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            imageUserIcon.setImageDrawable(resource);
                        }
                    });
        }
        if (!user.getPetname().equals("")) {
            tvUserNickname.setText(user.getPetname());
        } else {
            tvUserNickname.setText("您还没有设置昵称快去设置吧");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    cropPick(photoUri);
                }
                break;
            case REQUEST_IMAGE_FORM_PICK:
                if (resultCode == RESULT_OK) {
                    cropPick(data.getData());
                }
                break;
            //预览图片
            case UCrop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Uri uri = UCrop.getOutput(data);
                        if (Utils.isOnline(this)) {
                            if (uri != null) {
                                editInfoPresenter.postImageToQiniuYun(uri);
                            } else {
                                MyToastUtils.showToast(this, "图片和昵称不能为空");
                            }
                        } else {
                            MyToastUtils.showToast(this, "哎呀，好像没有网络");
                        }
                    }
                }
                break;
        }
    }


    /**
     * 裁剪图片
     *
     * @param uri 文件的provider路径
     */
    private void cropPick(Uri uri) {
        if (uri != null) {
            String fileName = "litBookScholar";
            File dir = new File(getCacheDir(), fileName);
            UCrop uCrop = UCrop.of(uri, Uri.fromFile(dir))
                    .withAspectRatio(1, 1)
                    .withMaxResultSize(100, 100);
            UCrop.Options options = new UCrop.Options();
            options.setHideBottomControls(true);
            options.setToolbarColor(getResources().getColor(R.color.colorPrimary));
            options.setToolbarWidgetColor(getResources().getColor(R.color.colorAccent));
            options.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            uCrop.withOptions(options);
            uCrop.start(UserInfoActivity.this);
        }
    }

    /**
     * 拍照完成后的存储路径
     *
     * @return
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMDD_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDirs = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDirs);
        String mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    /**
     * 拍照
     * 调用系统相机
     */
    private void dispatchTakePictureIntent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.CAMERA, "需要获取相机权限", REQUEST_CAMERA_ACCESS_PERMISSION);
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (photoFile != null) {
                    photoUri = FileProvider.getUriForFile(this,
                            "com.bitworkshop.litebookscholar.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    /**
     * 从相册获取图片
     */
    private void dispatchFromPhotoIntent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, "需要获取读取权限", REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_IMAGE_FORM_PICK);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_ACCESS_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                }
                break;
            case REQUEST_STORAGE_READ_ACCESS_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchFromPhotoIntent();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @Override
    public String getUserNickname() {
        return null;
    }

    @Override
    public void seccess() {
        Glide.with(this)
                .load(imageUrl)
                .into(imageUserIcon);
    }

    @Override
    public void showLoading() {
        ProgressDialogUtil.showProgressBar(this, "上传中");
    }

    @Override
    public void hideLoading() {
        ProgressDialogUtil.hideProgressDiaglog();
    }

    @Override
    public void showError(String errorMsg) {
        MyToastUtils.showToast(this, errorMsg);
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        System.out.println("参数" + userAccount + "\n" + password);
        editInfoPresenter.updateUserInfo(userAccount, password, imageUrl, nickname);
    }

    @Override
    public void gotoCamera() {
        dispatchTakePictureIntent();
    }

    @Override
    public void gotoPhotos() {
        dispatchFromPhotoIntent();
    }
}
