package Requests;

import java.util.List;

public interface ResponseCallBack{
    void onResponse(List<String> list);
    void onError(Exception exception);
}