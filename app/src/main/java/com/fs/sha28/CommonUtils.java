package com.fs.sha28;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class CommonUtils {
	
	/**
	 * Convert given bitmap image to Base64 String.
	 * @param bitmap: bitmap image to be converted into base64 string format 
	 * @return imageEncoded: Base64 conversion of given bitmap
	 */
	public static String bitmapToBase64(Bitmap bitmap){
		Bitmap immagex=bitmap;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	    byte[] b = baos.toByteArray();
	    String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
	    return imageEncoded;
	}
	
	/**
	 * Convert a base64 string to bitmap 
	 * @param base64: String of type base64
	 * @return decodedByte: bitmap, converted from string
	 */
	public static Bitmap base64ToBitmap(String base64){
		byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
		Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
		return decodedByte;
	}
	
	/**
	 * Converts given time in seconds to the specified String format.
	 * @param seconds: seconds, this must be a long value
	 * @param format: date/time format needed
	 * @return converted date, from milliseconds to given format
	 */
	public static String getDateTime(long seconds, String format){
		// Create a DateFormatter object for displaying date in specified format.
		SimpleDateFormat formatToReturn=new SimpleDateFormat(format);
		formatToReturn.setTimeZone(TimeZone.getDefault());
		if(String.valueOf(seconds).length()>10){
			seconds=seconds/1000;
		}
		return formatToReturn.format(seconds*1000);
	}

	/**
	 * Returns the actual width of screen of device in pixels
	 * @param context: context of activity
	 * @return width in pixels
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Context context){
		if(android.os.Build.VERSION.SDK_INT >= 13){
			DisplayMetrics metrics = new DisplayMetrics();
			((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
			return metrics.widthPixels;

		}else{
			Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).
					getDefaultDisplay();
			return display.getWidth();
		}
	}
	
	/**
	 * Returns the actual height of screen of device in pixels
	 * @param context: context of activity
	 * @return height in pixels
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Context context){
		if((android.os.Build.VERSION.SDK_INT >= 13)){
			DisplayMetrics metrics = new DisplayMetrics();
			((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
			return metrics.heightPixels;

		}else{
			Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).
					getDefaultDisplay();
			return display.getHeight();
		}
	}
	
	/**
	 * @param inString: string to be checked
	 * @return : true if string is type of email address, else otherwise.
	 */
	public static boolean isEmail(String inString) {
		CharSequence cs=inString;
		if(Patterns.EMAIL_ADDRESS.matcher(cs).matches())
			return true;
		else
			return false;
	}
	
	/**
	 * This method returns rounded bitmap
	 * @param bitmap: Image to be edited
	 * @return Round bitmap
	 */
	public static Bitmap getRoundCornerBitmap(Bitmap bitmap) {
        
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xffff0000;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setFilterBitmap(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, 20, 20, paint);

		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth((float) 4);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	/**
	 * More refined method for getting a round cornered bitmap
	 * @param context: Context of activity
	 * @param input: Bitmap
	 * @param pixels:
	 * @param w: int, Width of required bitmap
	 * @param h: int, Height of required bitmap
	 * @param squareTL: boolean, if we want top left corner to be square
	 * @param squareTR: boolean, if we want top right corner to be square
	 * @param squareBL: boolean, if we want bottom left corner to be square
	 * @param squareBR: boolean, if we want bottom right corner to be square
	 * @return round cornered bitmap
	 */
	public static Bitmap getRoundedCornerBitmap(Context context, Bitmap input,
												int pixels , int w , int h , boolean squareTL, boolean squareTR,
												boolean squareBL, boolean squareBR  ) {

	    Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);
	    final float densityMultiplier = context.getResources().getDisplayMetrics().density;

	    final int color = 0xffffffff;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, w, h);
	    final RectF rectF = new RectF(rect);

	    //make sure that our rounded corner is scaled appropriately
	    final float roundPx = pixels*densityMultiplier;

	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

	    //draw rectangles over the corners we want to be square
	    if (squareTL ){
	        canvas.drawRect(0, 0, w/2, h/2, paint);
	    }
	    if (squareTR ){
	        canvas.drawRect(w/2, 0, w, h/2, paint);
	    }
	    if (squareBL ){
	        canvas.drawRect(0, h/2, w/2, h, paint);
	    }
	    if (squareBR ){
	        canvas.drawRect(w/2, h/2, w, h, paint);
	    }

	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(input, 0,0, paint);

	    return output;
	}

	/**
	 * This method returns rounded bitmap, it even works for rectangles!
	 * @param bitmap: Image to be edited
	 * @param border: boolean, set this to true, if you want border around image
	 * @return Round bitmap
	 */
	public static Bitmap getCircleBitmap(Bitmap bitmap, boolean border) {

		int borderWidth=0;

	    final Paint paint = new Paint();

	    float radius = 0;

	    if (bitmap.getWidth() > bitmap.getHeight()) {
	    	radius = bitmap.getHeight() / 2;
	    } else {
	    	radius = bitmap.getWidth() / 2;
	    }

	    final int width = bitmap.getWidth() + borderWidth;
	    final int height = bitmap.getHeight() + borderWidth;

	    Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
	    paint.setAntiAlias(true);
	    paint.setShader(shader);
	    Canvas canvas = new Canvas(canvasBitmap);
	    canvas.drawCircle(radius, radius, radius, paint);
	    paint.setShader(null);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(0xff684321);
	    paint.setStrokeWidth(borderWidth);
	    if(border){
	    	canvas.drawCircle(radius, radius, radius - borderWidth / 2, paint);
	    }
	    return canvasBitmap;
	}
	
	/**
	 * This method returns true if there is data connection available of any kind
	 * @param context: Activity context
	 * @return true or false, depending of availability of data connection
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm
		= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		
		boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	 //   ConnectivityManager cm = (ConnectivityManager)Context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
	   
	     NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	     for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI")){
	            //if (ni.isConnectedOrConnecting())
	        //NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (netInfo != null) {
                for (int i = 0; i < netInfo.length; i++) {
                    if (netInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                    	haveConnectedWifi = true;
                        return true;  //<--  --  -- Connected
                        
                    }
                }
            }
            
            return false;
	        }
	        
	       
	        
//	        else{
//	        	 haveConnectedWifi = false;
//	        }
	                
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnectedOrConnecting())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	    
	}
	
	
	/**
	 * This methods returns the name of file who's path is given as parameter
	 * @param filepath: path of file
	 * @return name of file
	 */
	public static String getFileNameFromPath(String filepath){
		//get the file name from the file path to send to server
		int idx = filepath.lastIndexOf("/");
		String upfilename = idx >= 0 ? filepath.substring(idx + 1) : filepath;
		return upfilename;
	}
	
	/**
	 * This method converts the dps to pixels
	 * @param dp
	 * @param ctx
	 * @return
	 */
	public static int dpToPx(int dp, Context ctx)
	{
	    float density = ctx.getApplicationContext().getResources().getDisplayMetrics().density;
	    return Math.round((float)dp * density);
	}

}
