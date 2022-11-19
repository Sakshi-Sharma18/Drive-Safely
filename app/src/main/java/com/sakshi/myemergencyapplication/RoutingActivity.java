package com.sakshi.myemergencyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class RoutingActivity extends AppCompatActivity {

    List<Integer> path;
    private static int v;
    static int graph[][];
    static int via[][];
    static int rt[][];
    static int e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing);

        int user=0, nHospital=1;
        path = new ArrayList<Integer>();

        v = 11;
        e = 10;

        graph = new int[v][v];
        via = new int[v][v];
        rt = new int[v][v];
        for(int i = 0; i < v; i++)
            for(int j = 0; j < v; j++)
            {
                if(i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = 9999;
            }

        for(int i = 0; i < e; i++)
        {
            int s = path.get(i);
            s--;
            int d = path.get(i);
            d--;
            int c = 1;
            graph[s][d] = c;
            graph[d][s] = c;
        }

        int s = user;
        s--;
        int d = nHospital;
        d--;
        int c = 1;
        graph[s][d] = c;
        graph[d][s] = c;

    }

    static void update_table(int source)
    {
        for(int i = 0; i < v; i++)
        {
            if(graph[source][i] != 9999)
            {
                int dist = graph[source][i];
                for(int j = 0; j < v; j++)
                {
                    int inter_dist = rt[i][j];
                    if(via[i][j] == source)
                        inter_dist = 9999;
                    if(dist + inter_dist < rt[source][j])
                    {
                        rt[source][j] = dist + inter_dist;
                        via[source][j] = i;
                    }
                }
            }
        }
    }
}