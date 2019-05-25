//
//  UI.swift
//  ios-App
//
//  Created by Jeremy Rempel on 5/25/19.
//  Copyright © 2019 Jeremy Rempel. All rights reserved.
//

import app

public class UI: Kotlinx_coroutines_coreCoroutineDispatcher {
    
    override public func dispatch(context: KotlinCoroutineContext, block: Kotlinx_coroutines_coreRunnable) {
        DispatchQueue.main.async {
            block.run()
        }
    }
}
