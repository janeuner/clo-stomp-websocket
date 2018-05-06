/**
 * clo-util.js
 * Utility functions for CLO web client
 * 
 * Blame: Jarod Neuner <jarod@neuner.us>
 */

/**
 * Creates a RFC4122 v4 GUID using the JS crypto API
 * Source: https://stackoverflow.com/questions/105034/create-guid-uuid-in-javascript
 * @returns String containing a GUID
 */
function uuidv4() {
	  return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, c =>
	    (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
	  )
}