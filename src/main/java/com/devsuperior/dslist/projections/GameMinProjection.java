package com.devsuperior.dslist.projections;

import com.devsuperior.dslist.entities.Game;

public interface GameMinProjection {

    Long getId();
    String getTitle();
    Integer getYear();
    String getImgUrl();
    String getShortDescription();
    Integer getPosition();
}
