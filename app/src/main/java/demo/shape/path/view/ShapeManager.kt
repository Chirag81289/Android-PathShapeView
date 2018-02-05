package demo.shape.path.view

import android.graphics.Color
import android.graphics.PointF
import co.test.path.pathtest.ContourFillProvider
import shape.path.view.*
import shape.path.view.mark.Mark
import shape.path.view.point.converter.CoordinateConverter
import shape.path.view.point.converter.PercentagePointConverter
import kotlin.collections.ArrayList

/**
 * Created by Gleb on 1/29/18.
 */
class ShapeManager {

    fun getShapes(sample: Sample): List<PathShape> {
        when(sample) {
            Sample.SIMPLE_SHAPES -> return getSampleShapes()
            Sample.CONTOUR_SAMPLE -> return getContourSamples()
            Sample.GRADIENT_SAMPLE -> return getGradientSamples()
            Sample.SHAPE_SET_SAMPLE -> return getSetOfShapeSamples()
            Sample.POINT_CONVERTER_SAMPLE -> return getPointConverterSamples()
            Sample.MARKS_SAMPLE -> return getMarksSamples()
        }
    }

    private fun getSampleShapes(): List<PathShape> {
        //1st
        var list = arrayListOf<PathShape>()
        var pathProvider = PathProvider()
        var points = ArrayList<PointF>()
        points.add(PointF(0.1f, 0.9f))
        points.add(PointF(0.5f, 0.1f))
        points.add(PointF(0.9f, 0.9f))
        pathProvider.putLines(points, true, PathProvider.PathOperation.ADD)
        var body = BodyFillProvider()
        body.setColor(Color.GRAY)
        body.setRoundedCorners(30f)
        var contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(10f)
        contour.setRoundedCorners(30f)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter()))
         //2nd
        pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(10f)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter()))
        //3rd
        pathProvider = PathProvider()
        pathProvider.putOval(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        body = BodyFillProvider()
        body.setColor(Color.DKGRAY)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .setPointConverter(PercentagePointConverter()))
        //4th
        pathProvider = PathProvider()
        pathProvider.putArc(PointF(0.5f, 0.5f), 0.9f, 0.7f, 30f, 230f, PathProvider.PathOperation.ADD)
        contour = ContourFillProvider()
        contour.setColor(Color.LTGRAY)
        contour.setWidth(10f)
        body = BodyFillProvider()
        body.setColor(Color.BLUE)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter()))

        return list
    }

    private fun getContourSamples(): List<PathShape> {
        var list = arrayListOf<PathShape>()

        //1st
        var pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)

        var body = BodyFillProvider()
        body.setColor(Color.GRAY)
        var contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        contour.addDotParams(20f, 20f)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter()))
        //2nd
        pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        contour.addDotParams(20f, 40f)
        contour.addDotParams(40f, 40f)
        contour.addDotParams(40f, 40f)
        contour.setIsDotRounded(true)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter()))
        //3rd
        pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        body = BodyFillProvider()
        body.setColor(Color.DKGRAY)
        body.setRoundedCorners(50f)
        contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        contour.addDotParams(40f, 20f)
        contour.setRoundedCorners(50f)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter()))

        return list
    }

    private fun getGradientSamples(): List<PathShape> {
        var list = arrayListOf<PathShape>()

        //1st
        var pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)

        var body = BodyFillProvider()
        var gradient = GradientProvider()
        gradient.addColor(Color.BLACK)
                .addColor(Color.WHITE)
                .addColor(Color.BLACK)
                .setAngle(90f)
                .setType(GradientProvider.Type.LINEAR)
        body.setGradient(gradient)
        var contour = ContourFillProvider()
        contour.setWidth(20f)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .setPointConverter(PercentagePointConverter()))
        //2nd
        pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        contour = ContourFillProvider()
        gradient = GradientProvider()
        gradient.addColor(Color.BLACK)
                .addColor(Color.WHITE)
                .addColor(Color.BLACK)
                .setType(GradientProvider.Type.RADIAL)
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        body = BodyFillProvider()
        body.setGradient(gradient)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillContour(contour)
                .fillBody(body)
                .setPointConverter(PercentagePointConverter()))
        //3rd
        pathProvider = PathProvider()
        pathProvider.putRoundRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, 0.2f, PathProvider.PathOperation.ADD)
        gradient = GradientProvider()
        gradient.addColor(Color.BLUE)
                .addColor(Color.WHITE)
                .addColor(Color.BLUE)
                .setType(GradientProvider.Type.SWEEP)
        body = BodyFillProvider()
        body.setGradient(gradient)
        gradient = GradientProvider()
        gradient.addColor(Color.BLACK, 0f)
                .addColor(Color.RED,0.1f)
                .addColor(Color.WHITE,0.5f)
                .addColor(Color.RED,0.9f)
                .addColor(Color.BLACK,1f)
                .setType(GradientProvider.Type.LINEAR)
        contour = ContourFillProvider()
        contour.setGradient(gradient)
        contour.setWidth(100f)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter()))

        return list
    }

    private fun getPointConverterSamples(): List<PathShape> {
        var list = arrayListOf<PathShape>()

        //1st
        var pathProvider = PathProvider()
        pathProvider.putRect(PointF(200f, 200f), 100f, 100f, PathProvider.PathOperation.ADD)
        var body = BodyFillProvider()
        body.setColor(Color.LTGRAY)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body))
        //2nd
        pathProvider = PathProvider()
        pathProvider.putRect(PointF(200f, 200f), 100f, 100f, PathProvider.PathOperation.ADD)
        body = BodyFillProvider()
        body.setColor(Color.LTGRAY)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .setPointConverter(CoordinateConverter(400f, 500f)))
        //3rd
        pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        body = BodyFillProvider()
        body.setColor(Color.LTGRAY)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .setPointConverter(PercentagePointConverter()))

        return list
    }

    private fun getSetOfShapeSamples(): List<PathShape> {
        var list = arrayListOf<PathShape>()

        //1st
        var pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        pathProvider.putOval(PointF(0.5f, 0.5f), 0.6f, 1f, PathProvider.PathOperation.JOIN)
        pathProvider.putOval(PointF(0.5f, 0.5f), 1f, 0.6f, PathProvider.PathOperation.JOIN)
        var contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        var body = BodyFillProvider()
        body.setColor(Color.LTGRAY)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter()))
        //2nd
        pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        pathProvider.putOval(PointF(0.5f, 0.5f), 0.6f, 1f, PathProvider.PathOperation.INTERSECT)
        pathProvider.putOval(PointF(0.5f, 0.5f), 1f, 0.6f, PathProvider.PathOperation.INTERSECT)
        contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        body = BodyFillProvider()
        body.setColor(Color.LTGRAY)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter())
        )
        //3rd
        pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        pathProvider.putOval(PointF(0.5f, 0.5f), 0.6f, 1f, PathProvider.PathOperation.SUB)
        pathProvider.putOval(PointF(0.5f, 0.5f), 1f, 0.6f, PathProvider.PathOperation.SUB)
        contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        body = BodyFillProvider()
        body.setColor(Color.LTGRAY)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter())
        )
        //4th
        pathProvider = PathProvider()
        pathProvider.putRect(PointF(0.5f, 0.5f), 0.9f, 0.9f, PathProvider.PathOperation.ADD)
        pathProvider.putOval(PointF(0.5f, 0.5f), 0.6f, 0.9f, PathProvider.PathOperation.SUB_REVERSE)
        pathProvider.putOval(PointF(0.5f, 0.5f), 0.9f, 0.6f, PathProvider.PathOperation.SUB_REVERSE)
        contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        body = BodyFillProvider()
        body.setColor(Color.LTGRAY)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillBody(body)
                .fillContour(contour)
                .setPointConverter(PercentagePointConverter())
        )
        return list
    }

    private fun getMarksSamples(): List<PathShape> {
        var list = arrayListOf<PathShape>()
        var points = ArrayList<PointF>()
        points.add(PointF(0.1f, 0.9f))
        points.add(PointF(0.5f, 0.1f))
        points.add(PointF(0.9f, 0.9f))
        //1st
        var pathProvider = PathProvider()
        pathProvider.putLines(points, true, PathProvider.PathOperation.ADD)
        var contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        var mark = Mark()
        mark.setDrawable(R.drawable.mark)
        mark.fitDrawableToSize(50f,50f)
        mark.addPositions(points)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillContour(contour)
                .addMark(mark)
                .setPointConverter(PercentagePointConverter())
        )
        //2nd
        points = ArrayList()
        points.add(PointF(0.1f, 0.1f))
        points.add(PointF(0.5f, 0.3f))
        points.add(PointF(0.6f, 0.4f))
        points.add(PointF(0.7f, 0.6f))
        points.add(PointF(0.9f, 0.8f))

        pathProvider = PathProvider()
        pathProvider.putLines(points, false, PathProvider.PathOperation.ADD)
        contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        mark = Mark()
        mark.setDrawable(R.mipmap.ic_launcher)
        mark.fitDrawableToSize(50f,50f)
        var tc = TextConfigurator()
        tc.setTextColor(Color.BLUE)
        tc.setStyle(TextConfigurator.Style.BOLD, TextConfigurator.Style.UNDERLINE)
        tc.setTextSize(20f)
        tc.setTextOffset(PointF(0f, -30f))
        mark.setTextConfigurator(tc)
        points.forEach { mark.addPosition(it, it.toString()) }
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillContour(contour)
                .addMark(mark)
                .setPointConverter(PercentagePointConverter())
        )
        //3rd
        points = ArrayList()
        points.add(PointF(0.1f, 0.1f))
        points.add(PointF(0.5f, 0.3f))
        points.add(PointF(0.6f, 0.4f))
        points.add(PointF(0.7f, 0.6f))
        points.add(PointF(0.9f, 0.8f))
        points.add(PointF(0.1f, 0.9f))
        points.add(PointF(0.5f, 0.1f))
        points.add(PointF(0.9f, 0.9f))
        mark = Mark()
        mark.setDrawable(R.mipmap.ic_launcher)
        mark.fitDrawableToSize(50f,50f)
        mark.addPositions(points.subList(0,5))
        var mark2 = Mark()
        mark2.setDrawable(R.drawable.mark)
        mark2.fitDrawableToSize(50f,50f)
        mark2.addPositions(points.subList(5,8))

        pathProvider = PathProvider()
        pathProvider.putLines(points, false, PathProvider.PathOperation.ADD)
        contour = ContourFillProvider()
        contour.setColor(Color.BLACK)
        contour.setWidth(20f)
        list.add(PathShape.create()
                .setPath(pathProvider)
                .fillContour(contour)
                .addMark(mark)
                .addMark(mark2)
                .setPointConverter(PercentagePointConverter())
        )
        return list
    }
}