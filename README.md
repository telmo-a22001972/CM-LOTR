# Characters of LOTR offline

This is an improved version of "Characters of LOTR" that handles offline mode.

It uses the repository pattern and two alternative sources of information:

* LOTRServiceWithOkHttpAndJSONObject - connects to https://the-one-api.dev using okhttp and parses the response using JSONObject
* LOTRDBWithRoom - connects to a local database using room

# Some remarks

* There is an alternative implementation on the retrofit branch that uses retrofit instead of okhttp
* The LOTRRepository is a singleton initialized in the LOTRApplication (i.e., only once on application startup)
This way, LOTRRepository can be used in multiple activities/fragments.
* Since the repository is a singleton, it doesn't need to use a View Model
* LOTRCharacter must be Serializable to be able to be passed around activities/fragments. If performance is an issue, 
you should implement a CharacterUI class that is Parcelable but this adds a lof of boilerplate code to perform the
conversions. There is an example in the retrofit branch