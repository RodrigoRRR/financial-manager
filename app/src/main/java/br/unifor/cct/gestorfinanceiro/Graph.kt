package br.unifor.cct.gestorfinanceiro

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView


class Graph : AppCompatActivity() {

    private lateinit var LineGraphSeries<DataPoint> series:
    private lateinit var graphview: GraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        graphview = findViewById(R.id.graph_view_1)
    }
}