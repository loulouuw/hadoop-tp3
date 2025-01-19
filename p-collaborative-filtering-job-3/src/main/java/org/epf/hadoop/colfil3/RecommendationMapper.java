package org.epf.hadoop.colfil3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RecommendationMapper extends Mapper<Object, Text, Text, Text> {
    private Text userKey = new Text();
    private Text relationValue = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split("\t");

        if (tokens.length == 2) {
            String[] users = tokens[0].split(",");
            String score = tokens[1];

            if (users.length == 2) {
                // Relation réciproque
                userKey.set(users[0]);
                relationValue.set(users[1] + ":" + score);
                context.write(userKey, relationValue);

                userKey.set(users[1]);
                relationValue.set(users[0] + ":" + score);
                context.write(userKey, relationValue);
            }
        }
    }
}
