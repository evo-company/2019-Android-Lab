const functions = require('firebase-functions');

const admin = require('firebase-admin');
const app = admin.initializeApp();
const firestore = app.firestore();
const messaging = app.messaging();

exports.myFunctionName = functions.firestore
    .document('NOTIFICATIONS/{notificationId}')
    .onCreate((snap, context) => {
    	const newNotification = snap.data();
    	delete newNotification.date
    	delete newNotification.readList
    	const eventId = newNotification.eventId;
    	console.log("Notification: ", newNotification)
    	return getTokens(eventId)
    			.then(tokens => {
    				return messaging.sendToDevice(tokens, {data:toString(newNotification)});
    			})
});

function getTokens(eventId) {
	return firestore.collection("LISTENING_TOKENS").where('eventId', '==', eventId).get()
    		.then(querySnapshot => {
				var listOfTokens = [];
				querySnapshot.forEach(documentSnapshot => {
					listOfTokens.push(documentSnapshot.get('token'))
				});
				console.log("Tokens to send:", listOfTokens)
				return listOfTokens
			});
}

function toString(o) {
  Object.keys(o).forEach(k => {
    if (typeof o[k] === 'object') {
      return toString(o[k]);
    }
    
    o[k] = '' + o[k];
  });
  
  return o;
}   
