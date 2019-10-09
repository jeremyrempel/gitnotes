//
//  SettingsRepoSharedPref.swift
//  ios-App
//
//  Created by Jeremy Rempel on 10/6/19.
//  Copyright © 2019 Jeremy Rempel. All rights reserved.
//

import Foundation
import lib

/**
 * todo use settings
 */
class SettingsRepoSharedPref : SettingsRepo {
    
    func save(repoInfo: RepoInfo) {
    }
    
    func get() -> RepoInfo {
        return RepoInfo(user: "jeremyrempel", repo: "gitnotestest")
    }
}
