package org.epf.hadoop.colfil2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CommonFriendsReducer extends Reducer<Text, Text, Text, Text> {
    private Text result = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();

        for (Text value : values) {
            sb.append(value.toString()).append(",");
        }

        // Remove trailing comma
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        result.set(sb.toString());
        context.write(key, result);
    }
}
