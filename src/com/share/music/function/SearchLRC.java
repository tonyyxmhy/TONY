package com.share.music.function;   
  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.share.music.R;
/*  
     * 初期化，根据参数取得lrc的地址  
     */
// 将空格替换成+号   
// TODO Auto-generated catch block   
/*  
     * 根据lrc的地址，读取lrc文件流  
     * 生成歌词的ArryList  
     * 每句歌词是一个String  
     */
// number=0表示暂无歌词   
//                  Sentence sentence = new Sentence(s);   
/*
	 * 初期化，根据参数取得lrc的地址
	 */
// 将空格替换成+号
// TODO Auto-generated catch block
/*
	 * 根据lrc的地址，读取lrc文件流
	 * 生成歌词的ArryList
	 * 每句歌词是一个String
	 */
// number=0表示暂无歌词
//					Sentence sentence = new Sentence(s);
/** Called when the activity is first created. */
//      Baidu search = new Baidu("love the way you","michael jackson");   
public class SearchLRC {   
    private URL url;   
  
    public static final String DEFAULT_LOCAL = "GB2312";   
    StringBuffer sb = new StringBuffer();   
  
    /*  
     * 初期化，根据参数取得lrc的地址  
     */  
    public SearchLRC(String musicName, String singerName) {   
        // 将空格替换成+号   
        musicName = musicName.replace(' ', '+');   
        singerName = singerName.replace(' ', '+');   
        String strUrl = "http://box.zhangmen.baidu.com/x?op=12&title="  
                + musicName + "$$" + singerName + "$$$$";   
        Log.d("test", strUrl);   
        try {   
            url = new URL(strUrl);   
        } catch (Exception e1) {   
            e1.printStackTrace();   
        }   
        BufferedReader br = null;   
        String s;   
        try {   
            InputStreamReader in = new InputStreamReader(url.openStream());   
            Log.d("the encode is ", in.getEncoding());   
            br = new BufferedReader(in);   
        } catch (IOException e1) {   
            Log.d("tag", "br is null");   
        }   
        try {   
            while ((s = br.readLine()) != null) {   
                sb.append(s + "\r\n");   
            }   
            br.close();   
        } catch (IOException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
  
    }   
  
    /*  
     * 根据lrc的地址，读取lrc文件流  
     * 生成歌词的ArryList  
     * 每句歌词是一个String  
     */  
    public ArrayList fetchLyric() {   
        int begin = 0, end = 0, number = 0;// number=0表示暂无歌词   
        String strid = "";   
        begin = sb.indexOf("<lrcid>");   
        Log.d("test", "sb = " + sb);   
        if (begin != -1) {   
            end = sb.indexOf("</lrcid>", begin);   
            strid = sb.substring(begin + 7, end);   
            number = Integer.parseInt(strid);   
        }   
  
        String geciURL = "http://box.zhangmen.baidu.com/bdlrc/" + number / 100  
                + "/" + number + ".lrc";   
        Log.d("test", "geciURL = " + geciURL);   
        ArrayList gcContent =new ArrayList();   
        String s = new String();   
        try {   
            url = new URL(geciURL);   
        } catch (MalformedURLException e2) {   
            e2.printStackTrace();   
        }   
  
        BufferedReader br = null;   
        try {   
            br = new BufferedReader(new InputStreamReader(url.openStream(), "GB2312"));   
        } catch (IOException e1) {   
            e1.printStackTrace();   
        }   
        if (br == null) {   
            System.out.print("stream is null");   
        } else {   
            try {   
                while ((s = br.readLine()) != null) {   
//                  Sentence sentence = new Sentence(s);   
                    gcContent.add(s);   
                       
                }   
                br.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
  
        }   
        return gcContent;   
    }   
  
}  