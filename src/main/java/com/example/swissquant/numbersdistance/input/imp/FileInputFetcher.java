package com.example.swissquant.numbersdistance.input.imp;

import com.example.swissquant.numbersdistance.input.InputFetcher;
import com.example.swissquant.numbersdistance.model.Point;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Fetch input points from File system
 */
@AllArgsConstructor
@Slf4j
public class FileInputFetcher implements InputFetcher {
    private final String fileName;

    @Override
    public ConcurrentHashMap<Point, Long> getPointsConcurrentHashMap() {
        ConcurrentHashMap<Point, Long> pointConcurrentHashMap = new ConcurrentHashMap<>();
        byte[] bytes = new byte[0];
        // Thread safe: https://www.cryptacular.org/javadocs/org/cryptacular/io/ClassPathResource.html
        try(InputStream in = new ClassPathResource(fileName).getInputStream()){
             bytes = IOUtils.toByteArray(in);
            long start = System.currentTimeMillis();
            for (int i = 0; i < bytes.length; i = i + 4) {
                short x = ByteBuffer.wrap(new byte[]{bytes[i], bytes[i+1]}).getShort();
                short y = ByteBuffer.wrap(new byte[]{bytes[i+2], bytes[i+3]}).getShort();

                pointConcurrentHashMap.put(new Point(x,y), System.currentTimeMillis());
            }
            long duration = (System.currentTimeMillis() - start) / 1000;
            log.info("Set {} points within {} ms", pointConcurrentHashMap.size(), duration);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Corrupt input file. Partial failure when parsing input points. num of points parsed: {}, total points in input: {}",
                    pointConcurrentHashMap.size(), bytes.length);
        }finally {
            return pointConcurrentHashMap;
        }
    }
}
