package com.bsw

import io.prediction.controller.PDataSource
import io.prediction.controller.EmptyEvaluationInfo
import io.prediction.controller.EmptyActualResult
import io.prediction.controller.Params
import io.prediction.data.storage.Event
import io.prediction.data.store.PEventStore

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors

import grizzled.slf4j.Logger

case class DataSourceParams(
  appName: String,
  evalK: Option[Int]  // define the k-fold parameter.
) extends Params

class DataSource(val dsp: DataSourceParams)
  extends PDataSource[TrainingData,
      EmptyEvaluationInfo, Query, ActualResult] {

  @transient lazy val logger = Logger[this.type]

  override
  def readTraining(sc: SparkContext): TrainingData = {

    val labeledPoints: RDD[LabeledPoint] = PEventStore.aggregateProperties(
      appName = dsp.appName,
      entityType = "product",
      // only keep entities with these required properties defined
      required = Some(List("target", 
        "feat_1", 
        "feat_2", 
        "feat_3", 
        "feat_4", 
        "feat_5", 
        "feat_6", 
        "feat_7", 
        "feat_8", 
        "feat_9", 
        "feat_10", 
        "feat_11", 
        "feat_12", 
        "feat_13", 
        "feat_14", 
        "feat_15", 
        "feat_16", 
        "feat_17", 
        "feat_18", 
        "feat_19", 
        "feat_20", 
        "feat_21", 
        "feat_22", 
        "feat_23", 
        "feat_24", 
        "feat_25", 
        "feat_26", 
        "feat_27", 
        "feat_28", 
        "feat_29", 
        "feat_30", 
        "feat_31", 
        "feat_32", 
        "feat_33", 
        "feat_34", 
        "feat_35", 
        "feat_36", 
        "feat_37", 
        "feat_38", 
        "feat_39", 
        "feat_40", 
        "feat_41", 
        "feat_42", 
        "feat_43", 
        "feat_44", 
        "feat_45", 
        "feat_46", 
        "feat_47", 
        "feat_48", 
        "feat_49", 
        "feat_50", 
        "feat_51", 
        "feat_52", 
        "feat_53", 
        "feat_54", 
        "feat_55", 
        "feat_56", 
        "feat_57", 
        "feat_58", 
        "feat_59", 
        "feat_60", 
        "feat_61", 
        "feat_62", 
        "feat_63", 
        "feat_64", 
        "feat_65", 
        "feat_66", 
        "feat_67", 
        "feat_68", 
        "feat_69", 
        "feat_70", 
        "feat_71", 
        "feat_72", 
        "feat_73", 
        "feat_74", 
        "feat_75", 
        "feat_76", 
        "feat_77", 
        "feat_78", 
        "feat_79", 
        "feat_80", 
        "feat_81", 
        "feat_82", 
        "feat_83", 
        "feat_84", 
        "feat_85", 
        "feat_86", 
        "feat_87", 
        "feat_88", 
        "feat_89", 
        "feat_90", 
        "feat_91", 
        "feat_92", 
        "feat_93"
      )))(sc)
      // aggregateProperties() returns RDD pair of
      // entity ID and its aggregated properties
      .map { case (entityId, properties) =>
        try {
          LabeledPoint(properties.get[Double]("target"),
            Vectors.dense(Array(
 //properties.get[Double]("attr0"),
        properties.get[Int]("feat_1"), 
        properties.get[Double]("feat_2"), 
        properties.get[Double]("feat_3"), 
        properties.get[Double]("feat_4"), 
        properties.get[Double]("feat_5"), 
        properties.get[Double]("feat_6"), 
        properties.get[Double]("feat_7"), 
        properties.get[Double]("feat_8"), 
        properties.get[Double]("feat_9"), 
        properties.get[Double]("feat_10"), 
        properties.get[Double]("feat_11"), 
        properties.get[Double]("feat_12"), 
        properties.get[Double]("feat_13"), 
        properties.get[Double]("feat_14"), 
        properties.get[Double]("feat_15"), 
        properties.get[Double]("feat_16"), 
        properties.get[Double]("feat_17"), 
        properties.get[Double]("feat_18"), 
        properties.get[Double]("feat_19"), 
        properties.get[Double]("feat_20"), 
        properties.get[Double]("feat_21"), 
        properties.get[Double]("feat_22"), 
        properties.get[Double]("feat_23"), 
        properties.get[Double]("feat_24"), 
        properties.get[Double]("feat_25"), 
        properties.get[Double]("feat_26"), 
        properties.get[Double]("feat_27"), 
        properties.get[Double]("feat_28"), 
        properties.get[Double]("feat_29"), 
        properties.get[Double]("feat_30"), 
        properties.get[Double]("feat_31"), 
        properties.get[Double]("feat_32"), 
        properties.get[Double]("feat_33"), 
        properties.get[Double]("feat_34"), 
        properties.get[Double]("feat_35"), 
        properties.get[Double]("feat_36"), 
        properties.get[Double]("feat_37"), 
        properties.get[Double]("feat_38"), 
        properties.get[Double]("feat_39"), 
        properties.get[Double]("feat_40"), 
        properties.get[Double]("feat_41"), 
        properties.get[Double]("feat_42"), 
        properties.get[Double]("feat_43"), 
        properties.get[Double]("feat_44"), 
        properties.get[Double]("feat_45"), 
        properties.get[Double]("feat_46"), 
        properties.get[Double]("feat_47"), 
        properties.get[Double]("feat_48"), 
        properties.get[Double]("feat_49"), 
        properties.get[Double]("feat_50"), 
        properties.get[Double]("feat_51"), 
        properties.get[Double]("feat_52"), 
        properties.get[Double]("feat_53"), 
        properties.get[Double]("feat_54"), 
        properties.get[Double]("feat_55"), 
        properties.get[Double]("feat_56"), 
        properties.get[Double]("feat_57"), 
        properties.get[Double]("feat_58"), 
        properties.get[Double]("feat_59"), 
        properties.get[Double]("feat_60"), 
        properties.get[Double]("feat_61"), 
        properties.get[Double]("feat_62"), 
        properties.get[Double]("feat_63"), 
        properties.get[Double]("feat_64"), 
        properties.get[Double]("feat_65"), 
        properties.get[Double]("feat_66"), 
        properties.get[Double]("feat_67"), 
        properties.get[Double]("feat_68"), 
        properties.get[Double]("feat_69"), 
        properties.get[Double]("feat_70"), 
        properties.get[Double]("feat_71"), 
        properties.get[Double]("feat_72"), 
        properties.get[Double]("feat_73"), 
        properties.get[Double]("feat_74"), 
        properties.get[Double]("feat_75"), 
        properties.get[Double]("feat_76"), 
        properties.get[Double]("feat_77"), 
        properties.get[Double]("feat_78"), 
        properties.get[Double]("feat_79"), 
        properties.get[Double]("feat_80"), 
        properties.get[Double]("feat_81"), 
        properties.get[Double]("feat_82"), 
        properties.get[Double]("feat_83"), 
        properties.get[Double]("feat_84"), 
        properties.get[Double]("feat_85"), 
        properties.get[Double]("feat_86"), 
        properties.get[Double]("feat_87"), 
        properties.get[Double]("feat_88"), 
        properties.get[Double]("feat_89"), 
        properties.get[Double]("feat_90"), 
        properties.get[Double]("feat_91"), 
        properties.get[Double]("feat_92"), 
        properties.get[Double]("feat_93")
            ))
          )
        } catch {
          case e: Exception => {
            logger.error(s"Failed to get properties ${properties} of" +
              s" ${entityId}. Exception: ${e}.")
            throw e
          }
        }
      }.cache()

    new TrainingData(labeledPoints)
  }

  override
  def readEval(sc: SparkContext)
  : Seq[(TrainingData, EmptyEvaluationInfo, RDD[(Query, ActualResult)])] = {
    require(!dsp.evalK.isEmpty, "DataSourceParams.evalK must not be None")

    // The following code reads the data from data store. It is equivalent to
    // the readTraining method. We copy-and-paste the exact code here for
    // illustration purpose, a recommended approach is to factor out this logic
    // into a helper function and have both readTraining and readEval call the
    // helper.
    val labeledPoints: RDD[LabeledPoint] = PEventStore.aggregateProperties(
      appName = dsp.appName,
      entityType = "product",
      // only keep entities with these required properties defined
      required = Some(List("target", 
        "feat_1", 
        "feat_2", 
        "feat_3", 
        "feat_4", 
        "feat_5", 
        "feat_6", 
        "feat_7", 
        "feat_8", 
        "feat_9", 
        "feat_10", 
        "feat_11", 
        "feat_12", 
        "feat_13", 
        "feat_14", 
        "feat_15", 
        "feat_16", 
        "feat_17", 
        "feat_18", 
        "feat_19", 
        "feat_20", 
        "feat_21", 
        "feat_22", 
        "feat_23", 
        "feat_24", 
        "feat_25", 
        "feat_26", 
        "feat_27", 
        "feat_28", 
        "feat_29", 
        "feat_30", 
        "feat_31", 
        "feat_32", 
        "feat_33", 
        "feat_34", 
        "feat_35", 
        "feat_36", 
        "feat_37", 
        "feat_38", 
        "feat_39", 
        "feat_40", 
        "feat_41", 
        "feat_42", 
        "feat_43", 
        "feat_44", 
        "feat_45", 
        "feat_46", 
        "feat_47", 
        "feat_48", 
        "feat_49", 
        "feat_50", 
        "feat_51", 
        "feat_52", 
        "feat_53", 
        "feat_54", 
        "feat_55", 
        "feat_56", 
        "feat_57", 
        "feat_58", 
        "feat_59", 
        "feat_60", 
        "feat_61", 
        "feat_62", 
        "feat_63", 
        "feat_64", 
        "feat_65", 
        "feat_66", 
        "feat_67", 
        "feat_68", 
        "feat_69", 
        "feat_70", 
        "feat_71", 
        "feat_72", 
        "feat_73", 
        "feat_74", 
        "feat_75", 
        "feat_76", 
        "feat_77", 
        "feat_78", 
        "feat_79", 
        "feat_80", 
        "feat_81", 
        "feat_82", 
        "feat_83", 
        "feat_84", 
        "feat_85", 
        "feat_86", 
        "feat_87", 
        "feat_88", 
        "feat_89", 
        "feat_90", 
        "feat_91", 
        "feat_92", 
        "feat_93"
      )))(sc)
      // aggregateProperties() returns RDD pair of
      // entity ID and its aggregated properties
      .map { case (entityId, properties) =>
        try {
          LabeledPoint(properties.get[Double]("target"),
            Vectors.dense(Array(
 //properties.get[Double]("attr0"),
        properties.get[Double]("feat_1"), 
        properties.get[Double]("feat_2"), 
        properties.get[Double]("feat_3"), 
        properties.get[Double]("feat_4"), 
        properties.get[Double]("feat_5"), 
        properties.get[Double]("feat_6"), 
        properties.get[Double]("feat_7"), 
        properties.get[Double]("feat_8"), 
        properties.get[Double]("feat_9"), 
        properties.get[Double]("feat_10"), 
        properties.get[Double]("feat_11"), 
        properties.get[Double]("feat_12"), 
        properties.get[Double]("feat_13"), 
        properties.get[Double]("feat_14"), 
        properties.get[Double]("feat_15"), 
        properties.get[Double]("feat_16"), 
        properties.get[Double]("feat_17"), 
        properties.get[Double]("feat_18"), 
        properties.get[Double]("feat_19"), 
        properties.get[Double]("feat_20"), 
        properties.get[Double]("feat_21"), 
        properties.get[Double]("feat_22"), 
        properties.get[Double]("feat_23"), 
        properties.get[Double]("feat_24"), 
        properties.get[Double]("feat_25"), 
        properties.get[Double]("feat_26"), 
        properties.get[Double]("feat_27"), 
        properties.get[Double]("feat_28"), 
        properties.get[Double]("feat_29"), 
        properties.get[Double]("feat_30"), 
        properties.get[Double]("feat_31"), 
        properties.get[Double]("feat_32"), 
        properties.get[Double]("feat_33"), 
        properties.get[Double]("feat_34"), 
        properties.get[Double]("feat_35"), 
        properties.get[Double]("feat_36"), 
        properties.get[Double]("feat_37"), 
        properties.get[Double]("feat_38"), 
        properties.get[Double]("feat_39"), 
        properties.get[Double]("feat_40"), 
        properties.get[Double]("feat_41"), 
        properties.get[Double]("feat_42"), 
        properties.get[Double]("feat_43"), 
        properties.get[Double]("feat_44"), 
        properties.get[Double]("feat_45"), 
        properties.get[Double]("feat_46"), 
        properties.get[Double]("feat_47"), 
        properties.get[Double]("feat_48"), 
        properties.get[Double]("feat_49"), 
        properties.get[Double]("feat_50"), 
        properties.get[Double]("feat_51"), 
        properties.get[Double]("feat_52"), 
        properties.get[Double]("feat_53"), 
        properties.get[Double]("feat_54"), 
        properties.get[Double]("feat_55"), 
        properties.get[Double]("feat_56"), 
        properties.get[Double]("feat_57"), 
        properties.get[Double]("feat_58"), 
        properties.get[Double]("feat_59"), 
        properties.get[Double]("feat_60"), 
        properties.get[Double]("feat_61"), 
        properties.get[Double]("feat_62"), 
        properties.get[Double]("feat_63"), 
        properties.get[Double]("feat_64"), 
        properties.get[Double]("feat_65"), 
        properties.get[Double]("feat_66"), 
        properties.get[Double]("feat_67"), 
        properties.get[Double]("feat_68"), 
        properties.get[Double]("feat_69"), 
        properties.get[Double]("feat_70"), 
        properties.get[Double]("feat_71"), 
        properties.get[Double]("feat_72"), 
        properties.get[Double]("feat_73"), 
        properties.get[Double]("feat_74"), 
        properties.get[Double]("feat_75"), 
        properties.get[Double]("feat_76"), 
        properties.get[Double]("feat_77"), 
        properties.get[Double]("feat_78"), 
        properties.get[Double]("feat_79"), 
        properties.get[Double]("feat_80"), 
        properties.get[Double]("feat_81"), 
        properties.get[Double]("feat_82"), 
        properties.get[Double]("feat_83"), 
        properties.get[Double]("feat_84"), 
        properties.get[Double]("feat_85"), 
        properties.get[Double]("feat_86"), 
        properties.get[Double]("feat_87"), 
        properties.get[Double]("feat_88"), 
        properties.get[Double]("feat_89"), 
        properties.get[Double]("feat_90"), 
        properties.get[Double]("feat_91"), 
        properties.get[Double]("feat_92"), 
        properties.get[Double]("feat_93")
            ))
          )
        } catch {
          case e: Exception => {
            logger.error(s"Failed to get properties ${properties} of" +
              s" ${entityId}. Exception: ${e}.")
            throw e
          }
        }
      }.cache()
    
    // End of reading from data store

    // K-fold splitting
    val evalK = dsp.evalK.get
    val indexedPoints: RDD[(LabeledPoint, Long)] = labeledPoints.zipWithIndex

    (0 until evalK).map { idx =>
      val trainingPoints = indexedPoints.filter(_._2 % evalK != idx).map(_._1)
      val testingPoints = indexedPoints.filter(_._2 % evalK == idx).map(_._1)

      (
        new TrainingData(trainingPoints),
        new EmptyEvaluationInfo(),
        testingPoints.map {
          p => (new Query(p.features.toArray), new ActualResult(p.label))
        }
      )
    }
  }
}

class TrainingData(
  val labeledPoints: RDD[LabeledPoint]
) extends Serializable
