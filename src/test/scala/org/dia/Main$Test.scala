package org.dia

import org.dia.Constants.DATASET_VARS
import org.nd4j.linalg.factory.Nd4j
/**
 * import DSL for scala api
 */
import org.nd4j.api.linalg.DSL._

/**
 * Class for testing functionality
 */
class Main$Test extends org.scalatest.FunSuite {

  test("ReadingNCDFVarsToNdj4") {
    // loading TRMM data
    var url = "http://disc2.nascom.nasa.gov:80/opendap/TRMM_L3/TRMM_3B42_daily/1997/365/3B42_daily.1998.01.01.7.bin"
    val nd4jTRMM = Main.getNd4jNetCDFVars(url, DATASET_VARS.get("TRMM_L3").get)
    println(nd4jTRMM.toString)
    assert(true)
  }

  test("LoadDimensionsTRMM") {
    //get NCDFfile
    var url = "http://disc2.nascom.nasa.gov:80/opendap/TRMM_L3/TRMM_3B42_daily/1997/365/3B42_daily.1998.01.01.7.bin"
    val netcdfFile = Main.loadNetCDFDataSet(url, DATASET_VARS.get("TRMM_L3").get)
    assert(netcdfFile != null)

    //test dimensions
    val rows = Main.getRowDimension(netcdfFile)
    val cols = Main.getColDimension(netcdfFile)
    assert(rows == 400)
    assert(cols == 1440)
  }

//  test("Multiple Slicing") {
//
//    (1 to 100).foreach {i =>
//      val m = DenseMatrix.zeros[Double](i * 1000, i * 1000)//, (1000000 to 2000000).toArray.map(p => p.toDouble), 0)
//      val m2 = DenseMatrix.ones[Double](i * 1000, i * 1000)//, (1 to 1000000).toArray.map(p => p.toDouble), 0)
//      val start = System.nanoTime()
//      //val m3 = m * m2
//      val m3 = m :* m2
//      val stop = System.nanoTime()
//      println(stop - start)
//    }
//
////    val slice1 = m(1 to 3, 1 to 3)
////    assert(slice1(::, 1) === DenseVector(14, 15, 16))
////    assert(slice1(::, 1 to 2) === DenseMatrix((14, 20), (15, 21), (16, 22)))
//  }
//
//
//  test("BlockAvrgArrayTest") {
//    val squareSize = 100
//    val reductionSize = 50
//    val accuracy = 1E-4
//    val reducedWidth = squareSize / reductionSize
//    val testMatrix = DoubleMatrix.ones(squareSize, squareSize)
//    val resultMatrix = Main.jblasreduceResolution(testMatrix, reductionSize)
//
//    for (i <- 0 to (reducedWidth - 1)) {
//      for (j <- 0 to (reducedWidth - 1)) {
//        val error = Math.abs(resultMatrix.get(i, j) - 1)
//        if (error >= accuracy) {
//          assert(error >= accuracy, "The error is not even close for indices " + i + " " + j + "with value : " + resultMatrix.get(i, j))
//        }
//      }
//    }
//  }
//
//  /**
//   * Sets the values in the first row to be NaN's
//   * The average in the first element of the reduced Matrix should be
//   * 49/50. If not, then NaN's were not properly accounted for.
//   *
//   * TODO :: This test needs to fail - not sure why it isn't failing
//   */
//    test("BlockAvrgArrayNanTest") {
//      val squareSize = 100
//      val reductionSize = 50
//      val accuracy = 1E-15
//      val reducedWidth = squareSize / reductionSize
//      var testMatrix = DoubleMatrix.ones(squareSize, squareSize)
//      for(i <- 0 to squareSize) {
//        testMatrix = testMatrix.put(i, 0, Double.NaN)
//        testMatrix = testMatrix.put(i, 1, Double.NaN)
//        testMatrix = testMatrix.put(i, 2, Double.NaN)
//      }
//
//      val resultMatrix = Main.jblasreduceResolution(testMatrix, reductionSize)
//
//      for(i <- 0 to (reducedWidth - 1)) {
//        for (j <- 0 to (reducedWidth - 1)) {
//          val error = Math.abs(resultMatrix.get(i, j) - 1)
//          if (error >= accuracy) {
//            assert(error >= accuracy, "The error is not even close for indices " + i + " " + j + "with value : " + resultMatrix.get(i, j))
//          }
//        }
//      }
//    assert(true)
//  }

//  test("ND4JOps2dTest") {
//    (1 to 100).foreach{p =>
//      val m1 = Nd4j.create(p*1000 * p *1000).reshape(p * 1000,p * 1000)
//      val m2 = Nd4j.create(p*1000 * p *1000).reshape(p * 1000,p * 1000)
//      /**
//       * Vector subtraction
//       */
//      val start = System.nanoTime()
//      val m3 = m1 - m2
//      val stop = System.nanoTime()
//      println(stop - start)
//    }
//    assert(true)
//  }
//  test("breezeReduceResolutionAvrgTest") {
//    val squareSize = 100
//    val reductionSize = 50
//    val accuracy = 1E-15
//    val reducedWidth = squareSize / reductionSize
//    val testMatrix = DenseMatrix.ones[Double](squareSize, squareSize)
//
//    val resultMatrix = Main.breezereduceResolution(testMatrix, reductionSize)
//
//    for(i <- 0 to (reducedWidth - 1)){
//      for(j <- 0 to (reducedWidth - 1)) {
//        val error = Math.abs(resultMatrix(i, j) - 1)
//        if(error >= accuracy) {
//          assert(error >= accuracy, "The error is not even close for indices " + i + " " + j + "with value : " + resultMatrix(i, j))
//        }
//      }
//    }
//    assert(true)
//  }

//  test("ndf4jReduceResolutionAvrgTest") {
//        val squareSize = 100
//        val reductionSize = 50
//        val accuracy = 1E-15
//        val reducedWidth = squareSize / reductionSize
//        val testMatrix = Nd4j.create(squareSize, squareSize)
//
//        val resultMatrix = Main.Nd4jReduceResolution(testMatrix, reductionSize)
//
//        for(i <- 0 to (reducedWidth - 1)){
//          for(j <- 0 to (reducedWidth - 1)) {
//            val error = Math.abs(resultMatrix(i, j) - 1)
//            if(error >= accuracy) {
//              assert(error >= accuracy, "The error is not even close for indices " + i + " " + j + "with value : " + resultMatrix(i, j))
//            }
//          }
//        }
//        assert(true)
//  }
}