package com.example.janus.confinder.data;

import androidx.annotation.VisibleForTesting;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

// This is a simple implementation of a Retrofit client to obtain the Convention Events

public class RemoteConventionsEventService implements ConventionsEventService {

    private String baseURL = "https://fancons.com/events/map/sc-maps/";

    public interface ConventionsService {
        @GET("map-upcoming.xml")
        Call<ResponseBody> getConventions();
    }

    @Override
    public void getConventionEvents(final ConventionEventsCallback conventionEventsCallback) {

        // Create the Retrofit client
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseURL);

        Retrofit retrofit = retrofitBuilder.build();
        ConventionsService conventionEventsClient = retrofit.create(ConventionsService.class);

        Call<ResponseBody> conventionResults = conventionEventsClient.getConventions();

        // Call Retrofit asynchronously
        conventionResults.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = new String(response.body().bytes());
                    List<Convention> conventions = parseConventionsXML(body);
                    conventionEventsCallback.onConventionsComplete(conventions);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                conventionEventsCallback.onNetworkError();
            }
        });
    }

    private List<Convention> parseConventionsXML(String conventionsXML) {
        List<Convention> conventions = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document conventionsDocument = documentBuilder.parse(new InputSource(new StringReader("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n"  + conventionsXML)));
            Element conventionsElement = conventionsDocument.getDocumentElement();
            NodeList conventionsNodeList = conventionsElement.getElementsByTagName("marker");

            for (int conventionsIndex = 0; conventionsIndex < conventionsNodeList.getLength(); conventionsIndex++) {
                if (conventionsNodeList.item(conventionsIndex).getNodeType() == Node.ELEMENT_NODE) {
                    Element conventionElement = (Element) conventionsNodeList.item(conventionsIndex);
                    Convention newConvention = new Convention(
                            conventionElement.getAttribute("loc"),
                            Double.parseDouble(conventionElement.getAttribute("lng")),
                            conventionElement.getAttribute("name"),
                            conventionElement.getAttribute("dates"),
                            Integer.parseInt(conventionElement.getAttribute("id")),
                            conventionElement.getAttribute("type"),
                            conventionElement.getAttribute("urlname"),
                            Double.parseDouble(conventionElement.getAttribute("lat")),
                            conventionElement.getAttribute("status")
                    );
                    conventions.add(newConvention);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return conventions;
    }

    @VisibleForTesting
    public void setBaseURL (String baseURL) {
        this.baseURL = baseURL;
    }
}
