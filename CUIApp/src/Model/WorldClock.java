package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WorldClock {
    public static final String command = "worldclock";
    private final SimpleDateFormat gmtDateFormat 
            = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * 指定した場所の現在時刻を返す。指定がない場合はGMTを返す。
     * @param placeCode TOKYO, NY, SF, LONDON, SINGAPORE, MOSCOW, 空白のいずれかを指定。
     * @return 指定した場所の時刻。指定がなければGMT。存在しないコードを指定した場合はエラー文を返す。
     */
    public String fetchTimeOf(String placeCode) {
        placeCode = placeCode.replaceAll("\\s+", "");
        
        String timezone = "GMT";
        switch (placeCode) {
            case "TOKYO":
                timezone += "+9";
                break;
            case "NY":
                timezone += "-4";
                break;
            case "SF":
                timezone += "-7";
                break;
            case "LONDON":
                timezone += "+1";
                break;
            case "SINGAPORE":
                timezone += "+8";
                break;
            case "MOSCOW":
                timezone += "+3";
                break;
            case "":
                break;
            default:
                // 存在しないコードを指定した場合エラー文を返す
                return "invalid place code";
        }
        
        gmtDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return gmtDateFormat.format(new Date());
    }
}
