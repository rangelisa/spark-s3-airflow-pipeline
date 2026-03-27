import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import static org.apache.spark.sql.functions.*;

public class S3SparkExample {
    public static void main(String[] args) {

        System.setProperty("hadoop.home.dir", "C:\\hadoop");

        SparkSession spark = SparkSession.builder()
                .appName("S3 Example")
                .master("local[*]")
                .getOrCreate();

        spark.sparkContext().hadoopConfiguration()
                .set("fs.s3a.endpoint", "s3.amazonaws.com");

        spark.sparkContext().hadoopConfiguration()
                .set("fs.s3a.aws.credentials.provider",
                        "org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider");

        spark.sparkContext().hadoopConfiguration()
                .set("fs.s3a.access.key", "AWS_ACCESS_KEY_ID");

        spark.sparkContext().hadoopConfiguration()
                .set("fs.s3a.secret.key", "AWS_SECRET_ACCESS_KEY");

        Dataset<Row> df = spark.read()
                .option("header", "true")
                .csv("s3a://ir-meuarquivo/input/dados.csv");

        
        df.printSchema();
        df.show();

        
        Dataset<Row> dfProcessado = df
                .withColumn("idade_int", col("idade").cast("int")) 
                .filter(col("idade_int").gt(18)) 
                .withColumn("categoria",
                        when(col("idade_int").gt(30), "Experiente")
                                .otherwise("Jovem"));

        dfProcessado.show();

        
        dfProcessado.write()
                .mode("overwrite")
                .option("header", "true")
                .csv("s3a://ir-meuarquivo/output-processado");

        spark.stop();
    }
}
