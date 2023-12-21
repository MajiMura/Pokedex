package com.dev.pokedex.app.domain.model.pokemon_details

import com.squareup.moshi.Json

data class VersionGroupDetails(
    @Json(name = "level_learned_at")
    var levelLearnedAt: Int? = null,
    @Json(name = "move_learn_method")
    var moveLearnMethod: MoveLearnMethod? = MoveLearnMethod(),
    @Json(name ="version_group")
    var versionGroup: VersionGroup? = VersionGroup()
)