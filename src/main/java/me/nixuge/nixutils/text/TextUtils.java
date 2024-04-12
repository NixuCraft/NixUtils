package me.nixuge.nixutils.text;

public class TextUtils {
    public static String millisecondsToMMSSms(long milliseconds) {
    long totalSeconds = milliseconds / 1000;
    
    int minutes = (int) (totalSeconds / 60);
    int seconds = (int) (totalSeconds % 60);
    
    int millis = (int) (milliseconds % 1000);

    return String.format("%02d:%02d:%03d", minutes, seconds, millis);
    }

    public static String secondsToMMSS(int seconds) {
        return String.format("%02d:%02d", (seconds / 60) % 60, seconds % 60);
    }
    public static String suffixNumber(int number) {
        String num = String.valueOf(number);
        int numL = num.length();
        char lastChar = num.charAt(numL-1);

        // Check for 1_011th, 112th, 849_864_813th
        if (numL > 1 && num.charAt(numL-2) == '1') {
            return number+"th";
        }
        
        switch (lastChar) {
            case '1':
                return number+"st";
            case '2':
                return number+"nd";
            case '3':
                return number+"rd";
            default:
                return number+"th";
        }
    }
}
