package samples.ignite.hadoop.spark

class IgniteHadoopFileSystemWithSchema extends org.apache.ignite.hadoop.fs.v1.IgniteHadoopFileSystem {
    override def getScheme: String = "igfs"
}
