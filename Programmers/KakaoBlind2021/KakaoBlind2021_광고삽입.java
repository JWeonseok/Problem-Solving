package programmers;

public class KakaoBlind2021_광고삽입 {
	
	static long[] play;

	public static void main(String[] args) {
		System.out.println(solution("02:03:55", "00:14:15", new String[] {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}));
		System.out.println(solution("99:59:59", "25:00:00", new String[] {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));

	}
	
	static String solution(String play_time, String adv_time, String[] logs) {
		
        int playTime = convertSeconds(play_time);
        int advTime = convertSeconds(adv_time);
        
        play = new long[playTime + 1];
        
        if(playTime <= advTime) return "00:00:00";
        
        for (int i = 0; i < logs.length; i++) {
        	String[] tmp = logs[i].split("-");
			play[convertSeconds(tmp[0])]++;
			play[convertSeconds(tmp[1])]--;			
		}
        
        for (int i = 1; i <= playTime; i++) {
			play[i] += play[i-1];
		}
        
        for (int i = 1; i <= playTime; i++) {
			play[i] += play[i-1];
		}
        
        long end = play[advTime - 1], start = 0;
        
        for (int i = advTime; i <= playTime; i++) {
			long len = play[i] - play[i - advTime];
			if(len > end) {
				end = len;
				start = i - advTime + 1;
			}
		}
        return secToTime(start);
    }
	
	static int convertSeconds(String str) {
		String[] tmp = str.split(":");		
		return Integer.parseInt(tmp[0]) * 3600 + Integer.parseInt(tmp[1]) * 60 + Integer.parseInt(tmp[2]);
	}
	
	static String secToTime(long sec) {
		long h = sec / 3600;
		long htmp = sec % 3600;
		long m = htmp / 60;
		long s = htmp % 60;
		
		String t = "";
		if(h / 10 == 0) t += "0" + Long.toString(h);
		else t += Long.toString(h);
		t += ":";
		
		if(m / 10 == 0) t += "0" + Long.toString(m);
		else t += Long.toString(m);
		t += ":";
		
		if(s / 10 == 0) t += "0" + Long.toString(s);
		else t += Long.toString(s);
		return t;
	}

}
