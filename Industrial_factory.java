package industrial_factory;
import java.io.*;
import java.math.*;
import java.sql.Time;

public class Industrial_factory {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        double moneyHr = 36.25;  //เงิน/ชั่วโมง
        Time timeIn = new Time(8, 0, 0);
        Time latetimeIn = new Time(8, 5, 0);
        Time timeOut = new Time(17, 0, 0);
        Time OttimeOut = new Time(17, 30, 0);
        double rateOt = (290.00 / 8.00) * 1.5; //290 เงินต่อวัน 8 hr / 8 คือ ชั่วโมงทำงาน/วัน 1.5 คือ ค่า ot ปกติ
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/brass/Documents/Industrial_factory/src/industrial_factory/working_time.txt"), "TIS-620"))) {
            String line;
            int o = 0;
            while ((line = in.readLine()) != null) {
                
                String[] s = line.split("\\|");
                if (s.length == 5) {
                    System.out.println("ลำดับที่ : " + o++);
                    String timeins[] = s[2].split(":");
                    String timeouts[] = s[4].split(":");
                    Time timeworkin = new Time(Integer.parseInt(timeins[0]), Integer.parseInt(timeins[1]), 0);
                    Time timeworkout = new Time(Integer.parseInt(timeouts[0]), Integer.parseInt(timeouts[1]), 0);
                    Long longtimeout = 0L;  
                    if (timeworkin.before(timeworkout)) {
                        if (timeworkin.before(latetimeIn)) {
                            Long longtimein = timeIn.getTime();
                            if (timeworkout.after(timeOut)) {
                                longtimeout = timeOut.getTime();
                                if (timeworkout.after(OttimeOut)) {
                                    BigDecimal min = BigDecimal.valueOf(timeworkout.getTime()).subtract(BigDecimal.valueOf(OttimeOut.getTime()));
                                    BigDecimal hours = min.divide(BigDecimal.valueOf(4050000L), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
                                    BigDecimal moneyOT = hours.multiply(BigDecimal.valueOf(rateOt)).setScale(2, RoundingMode.HALF_UP);
                                    //System.out.println(hours + "OT");
                                    System.out.println("ชั่วโมง OT : " + hours + " ชั่วโมง");
                                    System.out.println("เงินโอที : " + moneyOT + " บาท");
                                }
                            } else {
                                longtimeout = timeworkout.getTime();
                            }
                            BigDecimal min = BigDecimal.valueOf(longtimeout).subtract(BigDecimal.valueOf(longtimein));
                            BigDecimal hours = min.divide(BigDecimal.valueOf(4050000L), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
                            BigDecimal money = hours.multiply(BigDecimal.valueOf(moneyHr)).setScale(2, RoundingMode.HALF_UP);
                            System.out.println("ชื่อ : " + s[0]);
                            System.out.println("วันที่เข้าทำงาน : " + s[1]);
                            System.out.println("เวลาเข้างาน : " + s[2] + " น.");
                            System.out.println("วันที่ออกงาน : " + s[3]);
                            System.out.println("เวลาออกงาน : " + s[4] + " น.");
                            System.out.println("ชั่วโมงงาน : " + hours + " ชั่วโมง");
                            System.out.println("ค่าแรง/วัน : " + money + " บาท");
                            System.out.println("===========================================================");
                            
                        } else {
                            Long longtimein = timeworkin.getTime();
                            if (timeworkout.after(timeOut)) {
                                longtimeout = timeOut.getTime();
                                if (timeworkout.after(OttimeOut)) {
                                    BigDecimal min = BigDecimal.valueOf(timeworkout.getTime()).subtract(BigDecimal.valueOf(OttimeOut.getTime()));
                                    BigDecimal hours = min.divide(BigDecimal.valueOf(4050000L), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
                                    BigDecimal moneyOT = hours.multiply(BigDecimal.valueOf(rateOt)).setScale(2, RoundingMode.HALF_UP);
                                    System.out.println("ชั่วโมง OT : " + hours + "ชั่วโมง");
                                    System.out.println("เงินโอที : " + moneyOT + "บาท");
                                }
                            } else {
                                longtimeout = timeworkout.getTime();
                            }
                            BigDecimal min = BigDecimal.valueOf(longtimeout).subtract(BigDecimal.valueOf(longtimein));
                            BigDecimal hours = min.divide(BigDecimal.valueOf(4050000L), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
                            BigDecimal money = hours.multiply(BigDecimal.valueOf(moneyHr)).setScale(2, RoundingMode.HALF_UP);
                            System.out.println("ชื่อ : " + s[0]);
                            System.out.println("วันที่เข้าทำงาน : " + s[1]);
                            System.out.println("เวลาเข้างาน : " + s[2] + " น.");
                            System.out.println("วันที่ออกงาน : " + s[3]);
                            System.out.println("เวลาออกงาน : " + s[4] + " น.");
                            System.out.println("ชั่วโมงงาน : " + hours + "ชั่วโมง");
                            System.out.println("ค่าแรง/วัน : " + money + "บาท");
                            System.out.println("===========================================================");
                            
                        }
                    }
                }
                
            }
        }
    }

}