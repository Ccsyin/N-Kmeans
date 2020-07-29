import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadData {
    //从文件中读取数据
//    public ArrayList<Point> read(String fileName){
//        ArrayList<Point> points = new ArrayList<Point>();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(fileName));
//            String line=null;
//            double x,y;
//            while ((line=reader.readLine()) != null){
//                line = line.trim();
//                x=Double.valueOf(line.split("\\s+")[0]);
//                y=Double.valueOf(line.split("\\s+")[1]);
//                Point point = new Point(x,y);
//                points.add(point);
//            }
//            reader.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return points;
//    }

    public ArrayList<PointN> read(String fileName){
        ArrayList<PointN> points = new ArrayList<PointN>();
        try {
            Path path = Paths.get(fileName);
            List<String> allLines = Files.readAllLines(path);
//            List<String> allLines = allLinesNotTrim.stream().map(String::trim).collect(Collectors.toList());
            String lineOne = allLines.get(0);
            int numDimension = lineOne.trim().split("\\s+").length;

            for (String line : allLines) {
                double[] temp = new double[numDimension];
                for (int i = 0; i < numDimension; i++) {
                    temp[i] = Double.parseDouble(line.trim().split("\\s+")[i]);
                }
                PointN pointN = new PointN(temp);
                points.add(pointN);
            }

            /*BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int numDimension = numDimension(fileName);
            String line = null;
            double[] temp = new double[numDimension];

            int s =0;
            while ((line=reader.readLine()) != null){
                line = (reader.readLine()).trim();
                for (int i = 0; i < numDimension; i++) {
                    temp[i] = Double.parseDouble(line.split("\\s+")[i]);
                }
                points.add(new PointN(temp));
                s++;
            }*/
//            reader.close();
//            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    //统计每行数字个数
    public static int numDimension(String fileName) {
        int numDimension =0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            line = (reader.readLine()).trim();
            numDimension = line.split("\\s+").length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numDimension;
    }

    //统计数据总数
    public int sum(String fileName){
        int temp = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line=null;
            line = (reader.readLine()).trim();
            while ((line=reader.readLine()) != null){
                temp++;
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
