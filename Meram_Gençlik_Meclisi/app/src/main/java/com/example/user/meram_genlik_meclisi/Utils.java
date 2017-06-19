package com.example.user.meram_genlik_meclisi;

import android.content.Context;
import android.widget.Toast;

public class Utils {
	public static void print(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
