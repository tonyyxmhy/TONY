package com.share.music.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class Caller
{
  private static RequestCache requestCache = null;

  // ERROR //

  public static String createStringFromIds(int[] paramArrayOfInt)
  {
    String localObject;
    if (paramArrayOfInt == null)
    {
      localObject = "";
      return "";
    }
    String str = "";
    int i = paramArrayOfInt.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localObject = str;
        break;
      }
      int k = paramArrayOfInt[j];
      str = str + k + "+";
      localObject = str;
    }
    
    return localObject;
  }

  public static String doGet(String paramString)
    throws WSError
  {
    RequestCache localRequestCache = requestCache;
    String str1 = null;
    String str2;
    if (localRequestCache != null)
    {
      str1 = requestCache.get(paramString);
      if (str1 != null)
      {
        str2 = str1;
      }
    }
    while (true)
    {
      
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpGet localHttpGet = new HttpGet(paramString);
      try
      {
        HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpGet);
        HttpEntity localHttpEntity = localHttpResponse.getEntity();
        if (localHttpEntity != null)
        {
          str1 = convertStreamToString(localHttpEntity.getContent());
          if (requestCache != null)
            requestCache.put(paramString, str1);
        }
        str2 = str1;
      }
      catch (UnknownHostException localUnknownHostException)
      {
        WSError localWSError2 = new WSError();
        localWSError2.setMessage(localUnknownHostException.getLocalizedMessage());
        throw localWSError2;
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        while (true)
          localClientProtocolException.printStackTrace();
      }
      catch (SocketException localSocketException)
      {
        WSError localWSError1 = new WSError();
        localWSError1.setMessage(localSocketException.getLocalizedMessage());
        throw localWSError1;
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
      return str2;
    }
  }

  private static String convertStreamToString(InputStream content) {
	// TODO Auto-generated method stub
	return null;
}

public static void setRequestCache(RequestCache paramRequestCache)
  {
    requestCache = paramRequestCache;
  }
}