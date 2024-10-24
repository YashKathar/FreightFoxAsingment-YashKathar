package com.frightfox.pojo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MapResponse {
    private List<Route> routes;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Route {
        private List<Leg> legs;
        private String summary;

       
        @Getter
        @Setter
        @NoArgsConstructor
        public static class Leg {
            private Distance distance;
            private Duration duration;
            
            @Getter
            @Setter
            @NoArgsConstructor
            public static class Distance {
                private String text;

                
            }
            @Getter
            @Setter
            @NoArgsConstructor
            public static class Duration {
                private String text;

               
            }
        }
    }
}
