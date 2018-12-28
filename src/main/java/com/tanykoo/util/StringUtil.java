
package com.tanykoo.util;

import java.io.UnsupportedEncodingException;


public class StringUtil
{
  static final String encodingChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ =";
  
  public static String byte2hex(byte[] b)
  {
    String hs = "";
    String stmp = "";
    for (int n = 0; n < b.length; ++n) {
      stmp = Integer.toHexString(b[n] & 0xFF);
      if (stmp.length() == 1)
        hs = hs + "0" + stmp;
      else
        hs = hs + stmp;

    }
    
    

    return hs.toUpperCase();
  }

  public static byte[] hex2Byte(String b)
  {
    char[] data = b.toCharArray();
    int l = data.length;
    byte[] out = new byte[l >> 1];
    int i = 0;
    for (int j = 0; j < l; ) {
      int f = Character.digit(data[(j++)], 16) << 4;
      f |= Character.digit(data[(j++)], 16);
      out[i] = (byte)(f & 0xFF);
      ++i;
    }

    return out;
  }

  public static String encode(String source, String charset)
  {
    byte[] sourceBytes = null;
    try
    {
      sourceBytes = source.getBytes(charset);
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    int numGroups = sourceBytes.length / 3 + ((sourceBytes.length % 3 == 0) ? 0 : 1);
    byte[] targetBytes = new byte[4];
    char[] target = new char[4 * numGroups];

    for (int group = 0; group < numGroups; ++group) {
      convert3To4(sourceBytes, group * 3, targetBytes);
      for (int i = 0; i < targetBytes.length; ++i) {
        char tmp = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ =".charAt(targetBytes[i]);
        target[(i + 4 * group)] = tmp;
      }

    }

    return new String(target);
  }

  private static void convert3To4(byte[] source, int sourceIndex, byte[] target)
  {
    target[0] = (byte)(source[sourceIndex] >>> 2 & 0x3F);
    if (source.length > sourceIndex + 1)
    {
      target[1] = (byte)((source[sourceIndex] & 0x3) << 4 | source[(sourceIndex + 1)] >>> 4 & 0xF);
      if (source.length > sourceIndex + 2) {
        target[2] = (byte)((source[(sourceIndex + 1)] & 0xF) << 2 | (source[(sourceIndex + 2)] & 0xFF) >>> 6 & 0xF);
        target[3] = (byte)(source[(sourceIndex + 2)] & 0x3F);
      } else {
        target[2] = (byte)((source[(sourceIndex + 1)] & 0xF) << 2);
        target[3] = 65;
      }
    } else {
      target[1] = (byte)((source[sourceIndex] & 0x3) << 4);
      target[2] = 65;
      target[3] = 65;
    }
  }

  public static String decode(String source, String charset)
  {
    if (source.length() % 4 != 0)
      throw new RuntimeException("valid Base64 codes have a multiple of 4 characters");

    int numGroups = source.length() / 4;
    int numExtraBytes = (source.endsWith("=")) ? 1 : (source.endsWith("==")) ? 2 : 0;

    byte[] targetBytes = new byte[3 * numGroups];
    byte[] sourceBytes = new byte[4];
    for (int group = 0; group < numGroups; ++group) {
      for (int i = 0; i < sourceBytes.length; ++i) {
        sourceBytes[i] = (byte)Math.max(0, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ =".indexOf(source.charAt(4 * group + i)));
      }

      convert4To3(sourceBytes, targetBytes, group * 3);
    }
    String resultStr = null;
    try {
      resultStr = new String(targetBytes, 0, targetBytes.length - numExtraBytes, charset);
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return resultStr;
  }

  private static void convert4To3(byte[] source, byte[] target, int targetIndex)
  {
    target[targetIndex] = (byte)(source[0] << 2 | source[1] >>> 4);
    target[(targetIndex + 1)] = (byte)((source[1] & 0xF) << 4 | source[2] >>> 2);
    target[(targetIndex + 2)] = (byte)((source[2] & 0x3) << 6 | source[3]);
  }

  

  public static byte[] base642byte(String source)
  {
    if (source.length() % 4 != 0)
      throw new RuntimeException("valid Base64 codes have a multiple of 4 characters");

    int numGroups = source.length() / 4;
    int numExtraBytes = (source.endsWith("==")) ? 2 : (source.endsWith("=")) ? 1 : 0;

    byte[] targetBytes = new byte[3 * numGroups];
    byte[] sourceBytes = new byte[4];
    for (int group = 0; group < numGroups; ++group) {
      for (int i = 0; i < sourceBytes.length; ++i) {
        sourceBytes[i] = (byte)Math.max(0, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ =".indexOf(source.charAt(4 * group + i)));
      }

      convert4To3(sourceBytes, targetBytes, group * 3);
    }

    byte[] result = new byte[targetBytes.length - numExtraBytes];
    for (int i = 0; i < targetBytes.length - numExtraBytes; ++i) {
      result[i] = targetBytes[i];
    }

    return result;
  }

  public static String hex2base64(String hexStr)
  {
    byte[] sourceBytes = null;

    sourceBytes = hex2Byte(hexStr);

    int numGroups = sourceBytes.length / 3 + ((sourceBytes.length % 3 == 0) ? 0 : 1);

    byte[] targetBytes = new byte[4];
    char[] target = new char[4 * numGroups];

    for (int group = 0; group < numGroups; ++group) {
      convert3To4(sourceBytes, group * 3, targetBytes);
      for (int i = 0; i < targetBytes.length; ++i) {
        char tmp = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ =".charAt(targetBytes[i]);
        target[(i + 4 * group)] = tmp;
      }
    }
    return new String(target);
  }

  public static String base642Hex(String base64Str)
  {
    byte[] tmp = base642byte(base64Str);
    return byte2hex(tmp);
  }



  public static String byte2Base64(byte[] bytes)
  {
    int numGroups = bytes.length / 3 + ((bytes.length % 3 == 0) ? 0 : 1);

    byte[] targetBytes = new byte[4];
    char[] target = new char[4 * numGroups];

    for (int group = 0; group < numGroups; ++group) {
      convert3To4(bytes, group * 3, targetBytes);
      for (int i = 0; i < targetBytes.length; ++i) {
        char tmp = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ =".charAt(targetBytes[i]);
        target[(i + 4 * group)] = tmp;
      }
    }
    return new String(target);
  }
  
  public static byte[] String2byte(String string){
	  return string.getBytes();
		 
  }
  
  public static String leftappend0(int len, String str)
  {
	  int primarylen = str.length();
	  if(primarylen>=0){
		  for(int i = 0 ;i<len-primarylen;i++)
		  {
			  str = "0"+str;
		  }
	  }
	  return str;
  }
  public static String rightappendspace(int len, String str){
	  int primarylen = str.length();
	  for(int i = 0;i< len - primarylen;i++){
		  str = str+" ";
	  }
	  return str;
  }
}
