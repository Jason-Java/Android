package com.jason.okhttpsourceanalyse;

import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        NettyClient();
    }

    private void NettyClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.channel(NioSocketChannel.class)
                        .group(nioEventLoopGroup)
                        .option(ChannelOption.TCP_NODELAY, true)// 无阻塞
                        .option(ChannelOption.SO_KEEPALIVE, false)// 短连接
//                        .option(ChannelOption.SO_TIMEOUT, 10)// 超时 10秒
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline()
                                        .addLast(new ByteArrayDecoder());  //接收解码方式
//                                .addLast(new ByteArrayEncoder());  //发送编码方式
//                              .addLast(new ChannelHandle(ConnectService.this)); //处理数据接收
                            }
                        });

                ChannelFuture channelFuture = bootstrap.connect("192.168.1.116", 8999);


                channelFuture.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        boolean success = channelFuture.isSuccess();
                        System.out.println("success  " + success);
                    }
                });
                channelFuture.channel().writeAndFlush(new byte[]{1, 2, 3, 4, 5, 6});
            }
        }).start();
    }

}