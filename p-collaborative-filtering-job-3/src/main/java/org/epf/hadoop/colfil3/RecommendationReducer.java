package org.epf.hadoop.colfil3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecommendationReducer extends Reducer<Text, Text, Text, Text> {
    private Text result = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<Recommendation> recommendations = new ArrayList<>();

        // Collecte toutes les relations
        for (Text value : values) {
            String[] parts = value.toString().split(":");
            if (parts.length == 2) {
                String user = parts[0];
                int score = Integer.parseInt(parts[1]);
                recommendations.add(new Recommendation(user, score));
            }
        }

        // Trie et sélectionne les 5 meilleures recommandations
        Collections.sort(recommendations, Comparator.comparingInt(Recommendation::getScore).reversed());
        List<Recommendation> topRecommendations = recommendations.subList(0, Math.min(5, recommendations.size()));

        // Formatage du résultat
        StringBuilder sb = new StringBuilder();
        for (Recommendation rec : topRecommendations) {
            if (sb.length() > 0) sb.append(",");
            sb.append(rec.getUser()).append(":").append(rec.getScore());
        }

        result.set(sb.toString());
        context.write(key, result);
    }
}
