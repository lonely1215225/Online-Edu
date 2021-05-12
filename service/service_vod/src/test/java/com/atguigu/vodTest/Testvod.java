package com.atguigu.vodTest;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

public class Testvod {
    public static void main(String[] args) throws Exception {
//        String accessKeyId = "LTAI5tNxe19kcidoBN2sftWS";
//        String accessKeySecret = "t9CWdcbyMrrbrFWcaU0NtBrmhBfT8a";
//
//        String title = "测试讯飞音乐节视频.mp4";   //上传之后文件名称
//        String fileName = "/Users/taichiman/62E7CF4C8C5FD64E930E6004C338C62F.mp4";  //本地文件路径和名称
//        //上传视频的方法
//        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
//        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
//        request.setPartSize(5 * 1024 * 1024L);
//        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
//        request.setTaskNum(Runtime.getRuntime().availableProcessors()<<1);
//
//        UploadVideoImpl uploader = new UploadVideoImpl();
//        UploadVideoResponse response = uploader.uploadVideo(request);
//
//        if (response.isSuccess()) {
//            System.out.print("VideoId=" + response.getVideoId() + "\n");
//        } else {
//            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
//            System.out.print("VideoId=" + response.getVideoId() + "\n");
//            System.out.print("ErrorCode=" + response.getCode() + "\n");
//            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
//        }
//        getPlayAuth();
//        getPlayUrl();

    }
    //1 根据视频iD获取视频播放凭证
    public static void getPlayAuth() throws Exception {

        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient("LTAI5tNxe19kcidoBN2sftWS", "t9CWdcbyMrrbrFWcaU0NtBrmhBfT8a");

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
//        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        request.setVideoId("3eaab5662f184f068b76f3b1f95a5ea0");

        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        System.out.println("playAuth:" + response.getPlayAuth());
    }


    public static void getPlayUrl() throws ClientException {
        //创建初始化对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient("LTAI5tNxe19kcidoBN2sftWS", "t9CWdcbyMrrbrFWcaU0NtBrmhBfT8a");
        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
//        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request对象里面设置视频id
        request.setVideoId("3eaab5662f184f068b76f3b1f95a5ea0");
        //调用初始化对象里面的方法，传递request，获取数据
        GetPlayInfoResponse response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.println("PlayInfo.PlayURL =" + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.println("VideoBase.Title =" + response.getVideoBase().getTitle() + "\n");


    }
}
