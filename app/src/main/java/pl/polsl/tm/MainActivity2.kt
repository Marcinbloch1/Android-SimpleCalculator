package pl.polsl.tm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.PointsGraphSeries
import kotlin.math.absoluteValue

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val receivedIntent = intent
        val reNum = receivedIntent.getDoubleExtra("Re", 0.0)
        val imNum = receivedIntent.getDoubleExtra("Im", 0.0)
        val graph = findViewById<GraphView>(R.id.graph)

        var max = Math.max(Math.abs(reNum), Math.abs(imNum))
        if(max==0.0) {
            max = 1.0
        }

        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.isYAxisBoundsManual = true

        graph.viewport.setMinY(-max)
        graph.viewport.setMaxY(max)
        graph.viewport.setMinX(-max)
        graph.viewport.setMaxX(max)

        val point = PointsGraphSeries(arrayOf<DataPoint>(DataPoint(reNum, imNum)))
        graph.addSeries(point)
    }
}