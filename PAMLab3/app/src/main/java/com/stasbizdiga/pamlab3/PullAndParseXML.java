package com.stasbizdiga.pamlab3;
import com.stasbizdiga.pamlab3.models.Post;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.io.InputStream;
import java.util.ArrayList;
import java.net.HttpURLConnection;


    public class PullAndParseXML {
        private String title;
        private String author;
        private String description;
        private String pubDate;
        private String link;

        private String feedUrl = null;
        private int READ_TIME_OUT = 5000;
        private int CONNECT_TIME_OUT = 10000;
        private XmlPullParserFactory xmlFactoryObject;
        public volatile boolean parsingComplete = true;

        private ArrayList<Post> postList = new ArrayList<Post>();


        public PullAndParseXML(String url) {
            this.feedUrl = url;
        }

        public String getTitle() {
            return title;
        }
        public String getAuthor() {return author;}
        public String getDescription() {return description;}
        public String getPubDate() {return pubDate;}
        public String getLink() {return link;}

        public ArrayList<Post> getPostList() {
            return postList;
        }


        public void parseXML(XmlPullParser xmlPullParser) {
            int event;
            String text = null;
            try {

                Post onePost = new Post();
                event = xmlPullParser.getEventType();

                while (event != XmlPullParser.END_DOCUMENT) {
                    String name = xmlPullParser.getName();

                    switch (event) {
                        case XmlPullParser.START_TAG:
                            if (name.equalsIgnoreCase("id"))
                            {
                                onePost = new Post();
                            }
                            switch(name){
                            case "link":
                            {
                                String href = xmlPullParser.getAttributeValue(null, "href");;
                                link = href;
                                onePost.setLink(link);
                                break;
                            }}
                            break;

                        case XmlPullParser.TEXT:
                            text = xmlPullParser.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            switch (name) {
                                case "id":
                                    postList.add(onePost);
                                    break;
                                case "title":
                                    title = text;
                                    onePost.setTitle(title);
                                    break;
                                case "name":
                                    author = text;
                                    onePost.setAuthor(author);
                                    break;
                                case "summary":
                                    description = text;
                                    onePost.setDescription(description);
                                    break;
                                case "updated":
                                    pubDate = text.replace("T","  ").replace("Z","");
                                    onePost.setPubDate(pubDate);
                                    break;
                                default:
                                break;
                            }
                            break;
                    }
                    event = xmlPullParser.next();
                }

                parsingComplete = false;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



        public void downloadXML() {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(feedUrl);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setReadTimeout(READ_TIME_OUT);
                        httpURLConnection.setConnectTimeout(CONNECT_TIME_OUT);
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.connect();
                        InputStream inputStream = httpURLConnection.getInputStream();

                        xmlFactoryObject = XmlPullParserFactory.newInstance();
                        XmlPullParser xmlPullParser = xmlFactoryObject.newPullParser();
                        xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                        xmlPullParser.setInput(inputStream, null);

                        parseXML(xmlPullParser);
                        inputStream.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
        }
    }
