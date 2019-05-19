//
//  ViewController.swift
//  ios-App
//
//  Created by Jeremy Rempel on 5/19/19.
//  Copyright © 2019 Jeremy Rempel. All rights reserved.
//

import UIKit
import app

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        //        let blah =  SampleKt.hello()
        
        let result = JsonTest().getPerson()
        
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 300, height: 21))
        label.center = CGPoint(x: 160, y: 285)
        label.textAlignment = .center
        label.font = label.font.withSize(25)
        label.text = result.lname + ", " + result.fname
        view.addSubview(label)
    }
}

