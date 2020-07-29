import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class WriteData {

    public static void toFile(ArrayList<ArrayList<PointN>> cluster, String file) {
        try{
            File file1 = new File(file);
            FileOutputStream fos = new FileOutputStream(file1);
            OutputStreamWriter dos = new OutputStreamWriter(fos);
            StringBuffer write = new StringBuffer();

//            for (ArrayList<PointN> points : cluster) {
//                for (PointN PointN : points) {
//                    write.append(PointN.getX())
//                            .append(" ")
//                            .append(PointN.getY()+" ");
//
//                }
//                write.append("\r\n");
//            }
            for (int i = 0; i < cluster.size() ; i++) {
                for (int j = 0; j < cluster.get(i).size(); j++) {
                    for (int k = 0; k < cluster.get(i).get(j).getValues().length; k++) {
                        write.append(cluster.get(i).get(j).getValues()[k])
                                .append(" ");
                    }
                    write.append(",");
                }
                write.append("\r\n");
            }
            dos.write(write.toString());
            dos.close();
        }catch (Exception e){

        }
    }


    public static void toFile2(ArrayList<PointN> q, String file) {
        try{
            File file1 = new File(file);
            FileOutputStream fos = new FileOutputStream(file1);
            OutputStreamWriter dos = new OutputStreamWriter(fos);
            StringBuffer write = new StringBuffer();

            for (int i = 0; i <q.size() ; i++) {
                for (int j = 0; j <q.get(i).getValues().length ; j++) {
                    write.append(q.get(i).getValues()[j])
                            .append(" ");
                }
                    write.append("\r");
            }
            dos.write(write.toString());
            dos.close();
        }catch (Exception e){

        }
    }

}
