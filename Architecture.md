# Architecture

##UI Layer
HomePage - Shows the starting page with StarSign dropdown and generate button
PredResultPage - Shows the resulting generated prediction
TarotResultPage - Shows the resuting generated tarot card predcition

##Logic Layer
IRetrivePredcition - Gets a random prediction from from the persistance layer and allows preforming of some dynamic
	string replacement within predictions
IRetriveStarSign - Gets the star signs from the persistance layer
IRetriveUser - Gets the primary user object from the persistance layer
IRetriveTarot - Gets a random tarot card from the percistance layer and allows for preforming of some dynamic string 	replacement within the prediction associated with each card

##Persistance Layer
IPredictionPersistance - Interface for interacting with Prediction objects in the database
IUserPersistance - Interface for interacting with User objects in the database
IStarSignPersistance - Interface for interacting with StarSign objects in the database
ITarotPersistance - Interface for interacting with Tarot objects in the database