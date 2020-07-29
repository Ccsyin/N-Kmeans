import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        //集合数
        int num;
        //最大迭代数
        int maxClusterTimes = 1000;
        //读取点总个数
        int sum;

        //生成1000个随机点
//        double x,y;
//        ArrayList<PointN> q= new ArrayList<PointN>();
//        Random rd = new Random();
//        for (int i = 0; i <1000 ; i++) {
//            PointN a = new PointN(rd.nextInt(100),rd.nextInt(100));
//            q.add(a);
//        }
//        WriteData.toFile2(q,"/Users/chenshiyin/IdeaProjects/k-means/random.txt");

        //从文件中读取点集合
        ArrayList<PointN> points = new ArrayList<PointN>();
        ArrayList<PointN> pointsCopy = new ArrayList<PointN>();
        ReadData read = new ReadData();
        String fileName="/Users/chenshiyin/IdeaProjects/k-means/threecircles.txt";
        points = read.read(fileName);
        pointsCopy = read.read(fileName);
        sum=read.sum(fileName);

        //输入种子数
        System.out.println("请输入您需要将这些点分为几类？");
        num=str.nextInt();
        while (num>points.size()){
            System.out.println("您输入的数量小于点集合中总数"+sum+",请重新输入您需要将这些点分为几类？");
            num=str.nextInt();
        }

        Kmeans k = new Kmeans(num);
        k.setPoints(points);

        //初始化
        ArrayList<PointN> initialCenter = new ArrayList<PointN>();
        ArrayList<ArrayList<PointN>> cluster = new ArrayList<ArrayList<PointN>>();
        ArrayList<ArrayList<PointN>> initialKcluster = new ArrayList<ArrayList<PointN>>();
        //初始中心点
        initialCenter=k.initialCenter(points,num);
        System.out.println("初始中心种子：");
        for (PointN pointN : initialCenter) {
            PointN.toString(pointN);
        }


        //初始空簇
        cluster = k.initialCluster(points,initialCenter);
        //初始分类集合
        initialKcluster = k.initialKcluster(cluster,points,num);
//        for (int i = 0; i < num; i++) {
//            System.out.println("初始分类：");
//            System.out.println("第"+(i+1)+"类：");
//            for (int j = 0; j < initialKcluster.get(i).size(); j++) {
//                    PointN.toString(initialKcluster.get(i).get(j));
//            }
//
//        }


        ArrayList<PointN> preCenter = initialCenter;
        ArrayList<PointN> updateKcenter = new ArrayList<PointN>();
        ArrayList<ArrayList<PointN>> updateKcluster = new ArrayList<ArrayList<PointN>>();
        updateKcenter = k.updateKcenter(initialKcluster,num);

        //迭代
        for (int i=1;i<maxClusterTimes;i++){
            int j = 1;
            while (k.isKpointChange(preCenter,updateKcenter)){
                System.out.println("第"+(j+i)+"次中心种子：");
                for (PointN pointN : updateKcenter) {
                    PointN.toString(pointN);
                }
                updateKcluster=k.updateKcluster(cluster,points,updateKcenter);
                preCenter = updateKcenter;
                updateKcenter = k.updateKcenter(updateKcluster,num);
                j++;
            }
        }
        if(updateKcluster.size() != num)
            System.out.println("您的集合不适合分为"+num+"类，已将数据分为"+updateKcluster.size()+"类");

        //输出最后分类结果
//        System.out.println("-----------最后归类结果-----------");
//        for (int i=0;i<num;i++){
//            System.out.println("第"+(i+1)+"类为：");
//            for (PointN pointN : updateKcluster.get(i)) {
//                PointN.toString(pointN);
//            }
//        }
//        for (int i = 0; i < num; i++) {
//            ArrayList<PointN> classCluster = new ArrayList<PointN>();
//            classCluster= updateKcluster.get(i);
//            WriteData.toFile2(classCluster,"/Users/chenshiyin/IdeaProjects/k-means/k-means-result"+i+".txt");
//        }
        WriteData.toFile(cluster,"/Users/chenshiyin/IdeaProjects/k-means/k-means-threecircles.txt");
    }
}
