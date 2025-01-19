package org.epf.hadoop.colfil2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CommonFriendsMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text userPair = new Text();
    private Text similarity = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Exemple d'entrée : "user1,user2\t0.8"
        String line = value.toString();
        String[] tokens = line.split("\t");

        if (tokens.length == 2) {
            userPair.set(tokens[0]);  // "user1,user2"
            similarity.set(tokens[1]);  // "0.8"
            context.write(userPair, similarity);
        }
    }
}
