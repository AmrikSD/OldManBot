package twitter;

import net.dv8tion.jda.core.JDA;
import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterListener{
	
	public static ConfigurationBuilder getConfigBuilder() {
		System.out.println("connecting to twitter");
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("DtNQY62jzyvRzUjgy1ZHKmIuU");
		cb.setOAuthConsumerSecret("ok4W63fjWNlLZKPowkbCBRVxD7k8eZpQivFOyZXoXFmY2KRWFV");
		cb.setOAuthAccessToken("741423392-7OXT4R8lE8DQ31e2qrVeqpE9QKZegxZBZ06rcED3");
		cb.setOAuthAccessTokenSecret("NHNsHvvRwvoB9fYXCbczc7vwYpSfxvcNveo00JpZeX6xQ");

		return cb;
	}
	
	public static void StartListener(JDA jda) {
		ConfigurationBuilder cb = getConfigBuilder();
		TwitterStream ts = new TwitterStreamFactory(cb.build()).getInstance();
		
		String PotatoBlood = "121992051406536705";
		//String Politics = "138449783797972992";
		String cockandArse = "465154163983122443";

		
		
		
		
		UserStreamListener listener = new UserStreamListener() {
			

			@Override
			public void onStatus(Status status) {
				
				String message = status.getText();
				System.out.println(status.getUser().getId());
				
				jda.getGuildById(PotatoBlood).getTextChannelById(cockandArse).sendMessage(message).queue();
				
			}
			
			@Override
			public void onException(Exception arg0) {
				
				arg0.printStackTrace();
				jda.getGuildById(PotatoBlood).getTextChannelById(cockandArse).sendMessage(arg0.toString()).queue(); //Print error
				
				
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onBlock(User arg0, User arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDeletionNotice(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDirectMessage(DirectMessage arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFavorite(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFavoritedRetweet(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFollow(User arg0, User arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFriendList(long[] arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onQuotedTweet(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onRetweetedRetweet(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUnblock(User arg0, User arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUnfavorite(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUnfollow(User arg0, User arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserDeletion(long arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListCreation(User arg0, UserList arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListDeletion(User arg0, UserList arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListUpdate(User arg0, UserList arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserProfileUpdate(User arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserSuspension(long arg0) {
				// TODO Auto-generated method stub
				
			}
		};

		ts.addListener(listener);
		ts.user();
	}
	
}
