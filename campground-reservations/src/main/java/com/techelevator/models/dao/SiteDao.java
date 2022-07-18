package com.techelevator.models.dao;

import com.techelevator.models.dto.Site;

import java.time.LocalDate;
import java.util.List;

public interface SiteDao {

    public Site getSite(int id);
    public List<Site> getAllSites();
    public List<Site> getAllSitesByCampground(int id);
    public List<Site> getAvailableSitesByCampground(int id, LocalDate fromDate, LocalDate toDate);
    public List<Site> getAvailableSitesByPark(int parkID, LocalDate fromDate, LocalDate toDate);
    public List<Site> searchSiteByNumber(int number);
    public List<Site> searchSiteByMaxOccupancy(int number, boolean over, boolean under);
    public List<Site> searchSiteByHandicap(boolean isHandicapped);
    public List<Site> searchSiteByRvLength(int number, boolean over, boolean under);
    public List<Site> searchSiteByUtilities(boolean utilities);


}
