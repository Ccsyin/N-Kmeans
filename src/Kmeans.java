import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kmeans {
    //k个簇中心
    private int k;
    //点集合列表
    private ArrayList<PointN> points;
    //中心点列表
    private ArrayList<PointN> center;
    //簇集合列表
    private ArrayList<ArrayList<PointN>> cluster;

    public Kmeans(int k){
        if (k<0){
            k=0;
        }
        else this.k=k;
    }

    public void setPoints(ArrayList<PointN> points) {
        this.points = points;
    }

    public ArrayList<PointN> getPoints() {
        return points;
    }

    public void setCenter(ArrayList<PointN> center) {
        this.center = center;
    }

    public ArrayList<PointN> getCenter() {
        return center;
    }

    public void setCluster(ArrayList<ArrayList<PointN>> cluster) {
        this.cluster = cluster;
    }

    public ArrayList<ArrayList<PointN>> getCluster() {
        return cluster;
    }

    //初始化中心点（在已有点中选取前K个不重复的点作为中心种子）
    public  ArrayList<PointN> initialCenter(ArrayList<PointN> points,int k){
        ArrayList<PointN> center = new ArrayList<PointN>();
        for (int i=0;i<k;i++){
            center.add(points.get(i));
        }
        return center;
    }

    //初始化K个中心点的k个簇
    public ArrayList<ArrayList<PointN>> initialCluster(ArrayList<PointN> points,ArrayList<PointN> center){
        ArrayList<ArrayList<PointN>> cluster = new ArrayList<ArrayList<PointN>>();
        //将前K个点分别放入K个簇
        for (int i=0;i<center.size();i++){
            ArrayList<PointN> a= new ArrayList<PointN>();
            a.add(center.get(i));
            cluster.add(a);
        }
        return cluster;
    }

    //将点集合进行初始分类
    public ArrayList<ArrayList<PointN>> initialKcluster(ArrayList<ArrayList<PointN>> cluster,ArrayList<PointN> points,int k){
        double[] mins = new double[k];
        int min=0;
        for (int i=0;i<points.size();i++){

            for (int j=0;j<k;j++){
                mins[j] = PointN.distance(points.get(i),cluster.get(j).get(0));
            }
            //求该点到哪个中心点的距离最短
            for (int j=1;j<k;j++){
                if (mins[min]>mins[j]){
                    min = j;
                }
            }
            cluster.get(min).add(points.get(i));
        }
        for (int i= 0;i<cluster.size();i++){
            cluster.get(i).remove(0);
        }
        return cluster;
    }

    //更新中心种子（求K个簇集合的重心）
    public ArrayList<PointN> updateKcenter(ArrayList<ArrayList<PointN>> cluster,int k){
        ArrayList<PointN> center = new ArrayList<PointN>();
//        double x,y;
//        double sumx=0;
//        double sumy=0;
//        for (int i=0;i<k;i++){
//            for (int j=0;j<cluster.get(i).size();j++){
//                sumx=cluster.get(i).get(j).getX()+sumx;
//                sumy=cluster.get(i).get(j).getY()+sumy;
//            }
////            x=(double) Math.round((sumx/cluster.get(i).size())*100)/100;
////            y=(double) Math.round((sumy/cluster.get(i).size())*100)/100;
//            x=(double) (sumx/cluster.get(i).size());
//            y=(double) (sumy/cluster.get(i).size());
//            center.add(new PointN(x,y));
//            sumx=0;
//            sumy=0;
//        }
        double[][] values = new double[cluster.size()][cluster.get(0).get(0).getValues().length];
        double sumValues = 0;
        //分类个数
        for (int i = 0; i <cluster.size() ; i++) {
            //第i类里第j个点中的第l列数组
            for (int l = 0; l < cluster.get(i).get(0).getValues().length; l++) {
            //第i类里点个数
            for (int j = 0; j < cluster.get(i).size(); j++) {
                    sumValues = sumValues+cluster.get(i).get(j).getValues()[l];
                }
                values[i][l] = sumValues/cluster.get(i).size();
                sumValues = 0;
            }
            center.add(new PointN(values[i]));
        }
        return center;
    }
    //更新簇集合
    public ArrayList<ArrayList<PointN>> updateKcluster(ArrayList<ArrayList<PointN>> cluster,ArrayList<PointN> points,ArrayList<PointN> center){
        for (int i=0;i<cluster.size();i++){
                cluster.get(i).clear();

        }
        for (int i=0;i<points.size();i++){
            double[] mins = new double[center.size()];
            int min=0;
            for (int j=0;j<center.size();j++){
                mins[j] = PointN.distance(points.get(i),center.get(j));
            }
            //求该点到哪个中心点的距离最短
            for (int j=1;j<center.size();j++){
                if (mins[min]>mins[j]){
                    min = j;
                }
            }
            cluster.get(min).add(points.get(i));
        }
        for (int i=0;i<cluster.size();i++){
            if (cluster.get(i).size()==0)
                cluster.remove(cluster.get(i));
        }
        return cluster;
    }

    //中心种子是否发生改变
    public boolean isKpointChange(ArrayList<PointN> preCenter,ArrayList<PointN> nowCenter){
        boolean is = true;
        int a = 0;
        for (int i=0;i<nowCenter.size();i++){
                PointN point1 =nowCenter.get(i);
                PointN point2 =preCenter.get(i);
                if (PointN.equal(point1,point2)){
                    a = a+1;
                }
        }
        if (nowCenter.size() == a){
            is = false;
        }
        return  is;
    }




}
