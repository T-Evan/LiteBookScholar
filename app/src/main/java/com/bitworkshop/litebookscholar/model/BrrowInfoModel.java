package com.bitworkshop.litebookscholar.model;

import android.content.res.Resources;

import com.bitworkshop.litebookscholar.App;
import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.api.Api;
import com.bitworkshop.litebookscholar.entity.BrrowInfo;
import com.bitworkshop.litebookscholar.entity.Brrowlist;
import com.bitworkshop.litebookscholar.retrofit.BrrowService;
import com.bitworkshop.litebookscholar.util.JsonConverterFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/11/5.
 */

public class BrrowInfoModel implements IBrrowInfoModel {
    private static final String TAG = BrrowInfoModel.class.getSimpleName();
    private Call<JSONObject> call;
    private BrrowService service;
    Brrowlist brrowlist;

    public BrrowInfoModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.LOGIC_BASE_URL)
                .addConverterFactory(JsonConverterFactory.create())
                .build();
        service = retrofit.create(BrrowService.class);
    }

    public void getBrrowInfo(final String brrowAccount, final String brrowPassword, final OnRequestListner<List<BrrowInfo.brrowdata>> bro) {
        call = service.getbrrowlist("booklist", brrowAccount, brrowPassword);
        call.enqueue(new Callback<JSONObject>() {
                         @Override
                         public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                             List<BrrowInfo.brrowdata> brrowdatas = new ArrayList<>();
                             System.out.println(response.body());
                             if (response.isSuccessful()) {
                                 try {
                                     JSONObject arr = response.body();
                                     JSONArray pem = arr.getJSONArray("booklist");
                                     for (int i = 0; i < pem.length(); i++) {
                                         BrrowInfo.brrowdata brrowdata = new BrrowInfo.brrowdata();
                                         JSONArray tem = (JSONArray) pem.get(i);
                                         for (int k = 0; k < 7; k++) {
                                             brrowdata.setBookId(tem.get(0).toString());
                                             String str = (decodeUnicode(tem.get(1).toString().replace("&#x", "\\u")).replace(";", ""));
                                             brrowdata.setBookTitle(str.substring(0, str.lastIndexOf("/")));

                                             String strr = tem.get(2).toString();
                                             String nstr = strr.substring(strr.lastIndexOf("="), strr.length());
                                             final String id = nstr.replace("=", "");
                                             brrowdata.setUrl(id);
                                             brrowdata.setBrrowTime(BrrowState(DateCompare(tem.get(4).toString().trim(),tem.get(3).toString().trim())));
                                             brrowdata.setEndTime(tem.get(4).toString());
                                             brrowdata.setNum(tem.get(5).toString());
                                             brrowdata.setBookLocation(tem.get(6).toString());
                                             brrowdata.setState(tem.get(7).toString());
                                         }
                                         brrowdatas.add(brrowdata);
                                     }
                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 } catch (Exception e) {
                                     e.printStackTrace();
                                 }
                                 System.out.println(brrowdatas.size());
                                 bro.Seccess(brrowdatas);
                             }

                         }

                         @Override
                         public void onFailure(Call<JSONObject> call, Throwable throwable) {
                             bro.Fiald(throwable.getMessage());
                         }
                     }

        );
    }


    public void continueBrrow() {
    }

    private static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    public static String DateCompare(String s1, String s2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(s1);
        Date d2 = sdf.parse(s2);
        Long str=( Math.abs(((d1.getTime() - d2.getTime()) / (24 * 3600 * 1000))));
        return  Long.toString(str);

    }
    public static String BrrowState(String str){

        String text = String.format(getResources().getString(R.string.brrowstate), str);
        return text;

    }
    private static Resources getResources() {
        Resources mResources = null;
        mResources = App.getContext().getResources();
        return mResources;
    }
}