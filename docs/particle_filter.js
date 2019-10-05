/*
The MIT License (MIT)

Copyright (c) 2013 Richard Teammco

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/


// simulation constants
var FPS = 10;
var B_SIZE = 20;
var P_SIZE = 6;
var R_SIZE = 20;
var NUM_PARTICLES = 100;
var R_WALK_MAX = 40;
var ESTIMATE_RADIUS = 20;
var R_WALK_FREQUENCY = 5;
var JUMP_DISTANCE = 40;

// simulation runtime variables
var canvas;
var ctx;
var maxDist;
var xPos;
var yPos;
var movedX;
var movedY;
var estimateX;
var estimateY;
var estimateWX;
var estimateWY;
var curFrame;
var running = false;
var moving = true;
var animationHandle;

// beacon and particle arrays
var beacons = Array();
var particles = Array();;


// Particle Object:
function Particle(){
	this.x = 0;
	this.y = 0;
	this.weight = 1.0;

	// randomize particle position
	this.randomize = function() {
		this.x = Math.floor(Math.random()*canvas.width);
		this.y = Math.floor(Math.random()*canvas.height);
	}

	// degrade weight: multiply current weight by the given amount
	//	(decimal between 0 and 1)
	this.degrade = function(weight) {
		this.weight *= weight;
	}

	// normalizes weight around the maximum such that the particle with
	//	the most weight will now have a weight of 1.0, and all other
	//	particles' weights will scale accordingly
	this.normalize = function(maxWeight) {
		if(maxWeight == 0){
			alert("error - maxWeight 0 (check weight function)");
			return false;
		}
		this.weight /= maxWeight;
	}

	// returns this particle's weight
	this.getWeight = function() {
		return this.weight;
	}

	// returns a NEW particle object with position and weight identical
	//	to this one
	this.clone = function() {
		var copy = new Particle();
		copy.x = this.x;
		copy.y = this.y;
		copy.weight = this.weight;
		return copy;
	}
}


// init: run at beginning of simulation, or to reset the simulation
function init(){
	canvas = document.getElementById("pf_canvas");
	ctx = canvas.getContext("2d");
	maxDist = Math.floor(
			Math.sqrt(canvas.width*canvas.width + canvas.height*canvas.height));
	xPos = Math.floor(Math.random()*canvas.width);
	yPos = Math.floor(Math.random()*canvas.height);
	updatePositionText(xPos, yPos);
	movedX = 0;
	movedY = 0;
	estimateX = 0;
	estimateY = 0;
	estimateWX = 0;
	estimateWY = 0;

	curFrame = -1;

	// set up beacons around the edges of the screen
	beacons = Array();
	beacons.push([20, 20]);
	beacons.push([canvas.width/2, 20]);
	beacons.push([canvas.width-20, 20]);
	beacons.push([20, canvas.height-20]);
	beacons.push([canvas.width/2, canvas.height-20]);
	beacons.push([canvas.width-20, canvas.height-20]);

	// set up particles
	particles = Array();
	for(var i=0; i<NUM_PARTICLES; i++){
		var p = new Particle();
		p.randomize();
		particles.push(p);
	}

	// if animation isn't already running, start it
	if(!running) {
		animationHandle = setTimeout(frame, 1000/FPS);
		running = true;
	}
}
$(document).ready(init); // start on page load


// Distance Formula: returns the distance between two given points
function distance(x1, y1, x2, y2){
	var distX = x1-x2;
	var distY = y1-y2;
	return Math.floor(Math.sqrt(distX*distX + distY*distY));
}


// computes distance weight between a
function getWeight(robotDistToBeacon, particleDistToBeacon){
	var diff = Math.abs(robotDistToBeacon - particleDistToBeacon);
	return (maxDist - diff) / maxDist;
}


// Particle Filter: run the actual particle filter algorithm here
function particleFilter(){

	// 0. approximate robot position using current particles
	var totalX = 0;
	var totalY = 0;
	var totalWX = 0;
	var totalWY = 0;
	var totalW = 0;
	for(var i=0; i<particles.length; i++){
		totalX += particles[i].x;
		totalY += particles[i].y;
		var weight = particles[i].getWeight();
		totalWX += (weight * particles[i].x);
		totalWY += (weight * particles[i].y);
		totalW += weight;
	}
	// direct average location of all particles
	estimateX = Math.floor(totalX / particles.length);
	estimateY = Math.floor(totalY / particles.length);
	// weighted average of all particles
	estimateWX = Math.floor(totalWX / totalW);
	estimateWY = Math.floor(totalWY / totalW);

	// 1. if mouse moved (i.e. the "agent" moved), update all particles
	//	by the same amount as the mouse movement
	if(movedX != 0 || movedY != 0) {
		for(var i=0; i<particles.length; i++) {
			particles[i].x += movedX;
			particles[i].y += movedY;
		}
	}

	// 2. do a random walk if on random walk frame
	if(R_WALK_FREQUENCY != 0 && (curFrame % R_WALK_FREQUENCY) == 0) {
		for(var i=0; i<particles.length; i++){
			var dX = Math.floor(Math.random() * (R_WALK_MAX+1)) - R_WALK_MAX/2;
			var dY = Math.floor(Math.random() * (R_WALK_MAX+1)) - R_WALK_MAX/2;
			particles[i].x += dX;
			particles[i].y += dY;
		}
	}

	// 3. estimate weights of every particle
	var maxWeight = 0;
	for(var i=0; i<particles.length; i++){
		var weightSum = 0;
		for(var j=0; j<beacons.length; j++){
			// get distance to beacon of both the particle and the robot
			var robotDistToBeacon = distance(xPos, yPos,
					beacons[j][0], beacons[j][1]);
			var particleDistToBeacon = distance(particles[i].x, particles[i].y,
					beacons[j][0], beacons[j][1]);
			weightSum += getWeight(robotDistToBeacon, particleDistToBeacon);
		}
		var weight = weightSum / beacons.length;
		particles[i].degrade(weight);
		if(weight > maxWeight)
			maxWeight = weight;
	}

	// 4. normalize weights
	var weightSum = 0;
	var goodParticles = Array();
	var badParticles = Array();
	for(var i=0; i<particles.length; i++){
		particles[i].normalize(maxWeight);
		weightSum += particles[i].getWeight();
	}

	// 5. resample: pick each particle based on probability
	var newParticles = Array();
	var numParticles = particles.length;
	for(var i=0; i<numParticles; i++){
		var choice = Math.random() * weightSum;
		var index = -1;
		do {
			index++;
			choice -= particles[index].getWeight();
		} while(choice > 0);
		newParticles.push(particles[index].clone());
	}
	particles = newParticles;

	// clear any movedX, movedY values
	movedX = 0;
	movedY = 0;
}


// animate the simulation (called once every frame)
function frame(){
	// if animation is paused, do nothing this frame
	if(!running) {
		animationHandle = setTimeout(frame, 1000/FPS);
		return;
	}
	// track current frame number
	curFrame += 1;

	// run the particle filter algorithm:
	particleFilter();

	// clear out last frame and draw:
	ctx.clearRect(0, 0, canvas.width, canvas.height);

	// draw all particles (blue)
	ctx.fillStyle = "#5050FF";
	for(var i=0; i<particles.length; i++){
		ctx.fillRect(particles[i].x-P_SIZE/2, particles[i].y-P_SIZE/2, P_SIZE, P_SIZE);
	}

	// draw all beacons (yellow)
	ctx.fillStyle = "#FFFF00";
	for(var i=0; i<beacons.length; i++){
		ctx.fillRect(
				beacons[i][0]-B_SIZE/2, beacons[i][1]-B_SIZE/2, B_SIZE, B_SIZE);
	}

	// draw the "robot" (white)
	ctx.fillStyle = "#FFFFFF";
	ctx.fillRect(xPos-R_SIZE/2, yPos-R_SIZE/2, R_SIZE, R_SIZE);

	// draw estimated (belief) position as determined by the particles (red)
	ctx.strokeStyle = "#FF0000";
	ctx.lineWidth = 3;
	ctx.beginPath();
		ctx.arc(estimateX, estimateY, ESTIMATE_RADIUS, 0, 2*Math.PI);
		ctx.stroke();

	// draw weighted estimated position (green)
	ctx.strokeStyle = "#00FF00";
	ctx.lineWidth = 3;
	ctx.beginPath();
		ctx.arc(estimateWX, estimateWY, ESTIMATE_RADIUS, 0, 2*Math.PI);
		ctx.stroke();

	animationHandle = setTimeout(frame, 1000/FPS);
}


// update robot position when mouse is moved over the canvas area
function updatePosition(event){
	// if paused, do nothing
	if(!running || !moving)
		return;

	// compute actual mouse x and y relative to the Canvas position in the page
	var rect = canvas.getBoundingClientRect();
	var mouseX = event.clientX - rect.left;
	var mouseY = event.clientY - rect.top;

	// compute the offset in x and y movement
	movedX = mouseX - xPos;
	movedY = mouseY - yPos;

	// if mouse moved too much, consider it a jump (a jump will not update the
	//	movement of the particles)
	if(Math.abs(movedX) > JUMP_DISTANCE || Math.abs(movedY) > JUMP_DISTANCE)
		movedX = movedY = 0;

	// update the robot's x and y position
	xPos = Math.floor(mouseX);
	yPos = Math.floor(mouseY);
	updatePositionText(xPos, yPos);
}