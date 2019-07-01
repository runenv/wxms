package com.ecp888.wxms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMatcher {
   public static void main(String[] args) {
	   Pattern errorIdPattern = Pattern
				.compile("[TFP]-([A-Z0-9]{3,4}-([0-9]{4}))");
	   Matcher matcher = errorIdPattern.matcher("F-USER-1000");
}
}
