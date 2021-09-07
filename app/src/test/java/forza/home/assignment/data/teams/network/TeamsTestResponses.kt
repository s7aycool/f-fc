package forza.home.assignment.data.teams.network

class TeamsTestResponses {
    companion object {

        const val oneTeamResponse = "[\n" +
                "    {\n" +
                "        \"name\": \"Arsenal FC\",\n" +
                "        \"national\": false,\n" +
                "        \"country_name\": \"England\"\n" +
                "    }\n" +
                "]"

        const val nullNameTeamResponse = "[\n" +
                "    {\n" +
                "        \"name\": null,\n" +
                "        \"national\": false,\n" +
                "        \"country_name\": \"England\"\n" +
                "    }\n" +
                "]"

        const val allNullsTeamResponse = "[\n" +
                "    {\n" +
                "        \"name\": null,\n" +
                "        \"national\": null,\n" +
                "        \"country_name\": null\n" +
                "    }\n" +
                "]"

        const val emptyObjectInArrayResponse = "[\n" +
                "    {\n" +
                "    }\n" +
                "]"

        const val emptyArrayResponse = "[\n" +
                "]"

        const val teamWithoutNameResponse = "[\n" +
                "    {\n" +
                "        \"national\": false,\n" +
                "        \"country_name\": \"England\"\n" +
                "    }\n" +
                "]"

        const val teamWithExtraFieldsResponse = "[\n" +
                "    {\n" +
                "        \"firstExtraField\": \"extra\",\n" +
                "        \"name\": \"Arsenal FC\",\n" +
                "        \"secondExtraField\": \"extra\",\n" +
                "        \"national\": false,\n" +
                "        \"thirdExtraField\": \"extra\",\n" +
                "        \"country_name\": \"England\",\n" +
                "        \"fourthExtraField\": \"extra\"\n" +
                "    }\n" +
                "]"

        const val teamWithDuplicatedFieldResponse = "[\n" +
                "    {\n" +
                "        \"name\": \"Arsenal FC\",\n" +
                "        \"name\": \"Arsenal FC\",\n" +
                "        \"national\": false,\n" +
                "        \"country_name\": \"England\"\n" +
                "    }\n" +
                "]"
    }
}
