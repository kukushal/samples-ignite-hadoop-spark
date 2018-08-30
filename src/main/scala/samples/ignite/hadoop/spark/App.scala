package samples.ignite.hadoop.spark

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.ignite.Ignition
import org.apache.spark.sql.{SaveMode, SparkSession}

object App {
    def main(args: Array[String]): Unit = {
        val ignite = Ignition.start("ignite.xml")
        try {
            val spark: SparkSession = SparkSession.builder()
                .appName("example-spark-scala-read-and-write-from-hdfs")
                .master("local")
                .config("spark.executor.instances", "2")
                .getOrCreate()
            val df = spark.read.json("person.json")

            val HDFS_MASTER = "igfs://igfs@/"
            val PARQUET_DIR = s"${HDFS_MASTER}user/hdfs/wiki/testwiki"
            val CSV_DIR = s"${HDFS_MASTER}user/hdfs/wiki/testwiki.csv"

            df.write.mode(SaveMode.Overwrite).parquet(PARQUET_DIR)

            df.write.mode(SaveMode.Overwrite).csv(CSV_DIR)

            val fs = FileSystem.get(URI.create(HDFS_MASTER), new Configuration())
            println(readDir(fs, CSV_DIR))
        }
        finally {
            ignite.close()
        }
    }

    private def readDir(fs: FileSystem, dir: String): String = {
        val res = new StringBuilder
        val it = fs.listFiles(new Path(dir), true)
        while (it.hasNext)
            res ++= scala.io.Source.fromInputStream(fs.open(it.next().getPath)).mkString
        res.mkString
    }
}
