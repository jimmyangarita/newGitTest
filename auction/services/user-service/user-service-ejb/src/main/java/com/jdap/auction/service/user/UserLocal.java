package com.jdap.auction.service.user;

//~--- non-JDK imports ---------------------------------------------------------

import com.jdap.auction.common.xml.user.BidderType;
import com.jdap.auction.common.xml.user.SellerType;
import com.jdap.auction.common.xml.user.UserType;
import com.jdap.auction.exceptions.AuctionServiceException;
import com.jdap.auction.model.User;

//~--- JDK imports -------------------------------------------------------------

import java.util.List;

import javax.ejb.Local;

/**
 * Interface description
 *
 *
 * @version        9.0, 2014.September.09 04:05 PM
 * @author         JDAP Corporation
 */
@Local
public interface UserLocal
{
    /**
     * Method description
     *
     *
     * @param sellerrequest
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    public SellerType addSeller( SellerType sellerrequest ) throws AuctionServiceException;

    /**
     * Method description
     *
     *
     * @param updatedSeller
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    public SellerType updateSeller( SellerType updatedSeller ) throws AuctionServiceException;

    /**
     * Method description
     *
     *
     * @param seller
     *
     * @throws AuctionServiceException
     */
    public void deleteSeller( SellerType seller ) throws AuctionServiceException;

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    public List<SellerType> getSellersType() throws AuctionServiceException;

    /**
     * Method description
     *
     *
     * @param bidderrequest
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    public BidderType addBidder( BidderType bidderrequest ) throws AuctionServiceException;

    /**
     * Method description
     *
     *
     * @param updatedBidder
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    public BidderType updateBidder( BidderType updatedBidder ) throws AuctionServiceException;

    /**
     * Method description
     *
     *
     * @param bidder
     *
     * @throws AuctionServiceException
     */
    public void deleteBidder( BidderType bidder ) throws AuctionServiceException;

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    public List<BidderType> getBiddersType() throws AuctionServiceException;

    /**
     * Method description
     *
     *
     * @param userName
     * @param password
     * @param <T>
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    public
    <T extends UserType> T getUserByUsernamePassword( String userName,
                                                      String password ) throws AuctionServiceException;

    /**
     * Method description
     *
     *
     * @param userName
     * @param <T>
     *
     * @return
     *
     * @throws AuctionServiceException
     */
    public <T extends UserType> T getUserByUsername( String userName ) throws AuctionServiceException;
}
