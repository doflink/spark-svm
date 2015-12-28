import org.apache.spark.SparkContext  
import org.apache.spark.SparkContext._  
import org.apache.spark.SparkConf  
import org.apache.spark.SparkContext  
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils
//import tw.edu.ntu.csie.liblinear._  
        
object LibLinear {  
    def main(args:Array[String]) {  
        val logFile = "/kddtrain"  
        val conf = new SparkConf().setAppName("Spark-LIBLINEAR")  
        val sc = new SparkContext(conf) 
 
        // Load training data in LIBSVM format.  
        val data = MLUtils.loadLibSVMFile(sc, logFile).cache()  
        

        // Run training algorithm to build the model
        val numIterations = 100
            
        //train model
        val model = SVMWithSGD.train(data, numIterations)  
        //val model = SparkLiblinear.train(data, "-c 4 -e 0.00035 ")  
          
        model.save(sc,"/tmp/SVMLibLinearModel")   

 
        //Test model
        val test_data = MLUtils.loadLibSVMFile(sc, "/kddtest")

        val LabelAndPreds = test_data.map { point =>  
            val prediction = model.predict(point.features)  
            (point.label, prediction)  
        }  
      
        val accuracy = LabelAndPreds.filter(r => r._1 == r._2).count.toDouble / test_data.count  
     
        println("Training Accuracy = " + accuracy)  
      
    }  
}  
