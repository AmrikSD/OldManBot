package com.potatoblood.oldman.twitter;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.potatoblood.oldman.Config;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
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

public class TwitterListener {

	public static ConfigurationBuilder getConfigBuilder() {
		ConfigurationBuilder cb = new ConfigurationBuilder();

		JSONObject twitterJSON = Config.getTwitterJSON();
		String APIKey = twitterJSON.get("API Key").toString();
		String APISecret = twitterJSON.get("API Secret").toString();
		String Token = twitterJSON.get("Token").toString();
		String TokenSecret = twitterJSON.get("Token Secret").toString();
		Boolean enabled = (Boolean) twitterJSON.get("Enabled");

		if (enabled) {
			System.out.println("Twitter Functionality enabled");
			cb.setDebugEnabled(true);
			cb.setOAuthConsumerKey(APIKey);
			cb.setOAuthConsumerSecret(APISecret);
			cb.setOAuthAccessToken(Token);
			cb.setOAuthAccessTokenSecret(TokenSecret);

			return cb;
		}
		return null;
	}

	public static void StartListener(JDA jda) {

		System.out.println("Getting twitter config");

		ConfigurationBuilder cb = getConfigBuilder();

		if (cb == null) {
			System.out.println("Twitter functinality is either disabled OR set up incorrectly!");
			System.out.println("Functionality requiring twitter will not be available, everything else should be ok.");
			return;
		} else {
			System.out.println("Connected to Twitter");
			System.out.println(
					"Please make sure your configuration is correct, if you need to change it, please restart Old Man.");
		}

		TwitterStream ts = new TwitterStreamFactory(cb.build()).getInstance();

		JSONObject twitterJSON = Config.getTwitterJSON();
		JSONArray twitAccountsAsJSON = (JSONArray) twitterJSON.get("Accounts");

		String guildName = (String) twitterJSON.get("Guild Name");

		ArrayList<String> knownGuilds = new ArrayList<>();

		for (int i = 0; i < jda.getGuilds().size(); i++) {
			knownGuilds.add(jda.getGuilds().get(i).getName());
		}

		if (!knownGuilds.contains(guildName)) {
			System.out.println("[WARNING] OldMan doesn't appear to be connected to " + guildName
					+ ". Twitter functinality will NOT work.");
			System.out.println("[WARNING] Perhaps your config file is incorrect, it is caps sensitive!");
			return;
		}

		Guild guild = jda.getGuildsByName(guildName, false).get(0);

		ArrayList<String> knownChannels = new ArrayList<>();
		for (int i = 0; i < guild.getTextChannels().size(); i++) {
			knownChannels.add(guild.getTextChannels().get(i).getName());
		}

		String channelName = (String) twitterJSON.get("Text Channel");
		if (!knownChannels.contains(channelName)) {
			System.out.println("[WARNING] OldMan can't find " + channelName + ". Twitter functionality will NOT work");
			System.out.println("[WARNING] Perhaps your config file is incorrect, it is caps sensitive!");
			return;
		}

		TextChannel textChannel = guild.getTextChannelsByName(channelName, true).get(0);

		UserStreamListener listener = new UserStreamListener() {

			@Override
			public void onStatus(Status status) {

				// WHEN A NEW STATUS IS POSTED
				String tweetersName = status.getUser().getScreenName();

				if (twitAccountsAsJSON.contains(tweetersName)) {
					textChannel.sendMessage(tweetersName + ": " + status.getText()).queue();
				}

			}

			@Override
			public void onException(Exception arg0) {

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
			}

			@Override
			public void onBlock(User arg0, User arg1) {
			}

			@Override
			public void onDeletionNotice(long arg0, long arg1) {
			}

			@Override
			public void onDirectMessage(DirectMessage arg0) {
			}

			@Override
			public void onFavorite(User arg0, User arg1, Status arg2) {
			}

			@Override
			public void onFavoritedRetweet(User arg0, User arg1, Status arg2) {
			}

			@Override
			public void onFollow(User arg0, User arg1) {
			}

			@Override
			public void onFriendList(long[] arg0) {
			}

			@Override
			public void onQuotedTweet(User arg0, User arg1, Status arg2) {
			}

			@Override
			public void onRetweetedRetweet(User arg0, User arg1, Status arg2) {
			}

			@Override
			public void onUnblock(User arg0, User arg1) {
			}

			@Override
			public void onUnfavorite(User arg0, User arg1, Status arg2) {
			}

			@Override
			public void onUnfollow(User arg0, User arg1) {
			}

			@Override
			public void onUserDeletion(long arg0) {
			}

			@Override
			public void onUserListCreation(User arg0, UserList arg1) {
			}

			@Override
			public void onUserListDeletion(User arg0, UserList arg1) {
			}

			@Override
			public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
			}

			@Override
			public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {
			}

			@Override
			public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
			}

			@Override
			public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
			}

			@Override
			public void onUserListUpdate(User arg0, UserList arg1) {
			}

			@Override
			public void onUserProfileUpdate(User arg0) {
			}

			@Override
			public void onUserSuspension(long arg0) {
			}
		};

		ts.addListener(listener);
		ts.user();
	}

}
