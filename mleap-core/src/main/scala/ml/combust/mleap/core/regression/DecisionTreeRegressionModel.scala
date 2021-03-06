package ml.combust.mleap.core.regression

import ml.combust.mleap.core.Model
import org.apache.spark.ml.linalg.Vector
import ml.combust.mleap.core.tree.{DecisionTree, Node}
import ml.combust.mleap.core.types.{ScalarType, StructType, TensorType}

/** Class for a decision tree regression model.
  *
  * @param rootNode root decision tree node
  * @param numFeatures number of features used in prediction
  */
case class DecisionTreeRegressionModel(rootNode: Node, numFeatures: Int) extends DecisionTree with Model {
  /** Alias for [[ml.combust.mleap.core.regression.DecisionTreeRegressionModel#predict]]
    *
    * @param features features for prediction
    * @return prediction
    */
  def apply(features: Vector): Double = predict(features)

  /** Predict value for given features.
    *
    * @param features features for predictoin
    * @return prediction
    */
  def predict(features: Vector): Double = {
    rootNode.predictImpl(features).prediction
  }

  override def inputSchema: StructType = StructType("features" -> TensorType.Double(numFeatures)).get

  override def outputSchema: StructType = StructType("prediction" -> ScalarType.Double.nonNullable).get

}
