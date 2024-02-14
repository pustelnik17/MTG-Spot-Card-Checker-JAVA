package Requests;

import java.util.List;

public interface ResponseCallBack{
    void onResponse(List<Float> list);
    void onError(Exception exception);
}