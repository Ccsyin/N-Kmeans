import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PointN {
    private double[] values;

    public PointN(double[] values){
        this.values = values;
    };


    public void setValues(double[] values) {
        this.values = values;
    }

    public double[] getValues() {
        return values;
    }

    //计算距离
    public static double distance(PointN point1, PointN point2){
        double distance;
        double temp=0;
        for (int i = 0; i < point1.values.length; i++) {
            temp=temp+((point1.values[i]-point2.values[i])*(point1.values[i]-point2.values[i]));
        }
        distance = Math.sqrt(temp);
        return distance;
    }

    //判断两点是否相同
    public static  boolean equal(PointN point1,PointN point2){
        boolean a =true;
        if (Arrays.equals(point1.values,point2.values)){
            a = true;
        }
        else{
            a = false;
        }
        return a;
    }

    public static void toString(PointN pointN){
            System.out.print("( ");
            for (int i = 0; i < pointN.getValues().length; i++) {
                System.out.print(pointN.values[i]+" ");
            }
            System.out.println(")");
    }
}
