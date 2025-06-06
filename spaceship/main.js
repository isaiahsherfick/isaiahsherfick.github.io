import * as THREE from 'three';

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(90, window.innerWidth / window.innerHeight, 0.1, 1000);
const renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);
const rotating_shapes = [];

// const plane_cube_side_len = 25;
// for (let x = 0; x < 100; x+=plane_cube_side_len) {
//     for (let y = 0; y < 100; y+=plane_cube_side_len ) {
//         const cube = create_green_mesh_cube(x, plane_cube_side_len, y);
//         rotating_shapes.push(cube);
//         scene.add(cube);
//     }
// }
const cube = create_green_mesh_cube(1,1,1);
rotating_shapes.push(cube);
scene.add(cube);


const material = new THREE.LineBasicMaterial( {color: 0x00ff00} );
const points = [];
points.push (new THREE.Vector3 ( -10,0,0  ));
points.push (new THREE.Vector3 (0,10,0));
points.push (new THREE.Vector3 (10,0,0));
const geometry = new THREE.BufferGeometry().setFromPoints(points);
const line = new THREE.Line(geometry, material);
scene.add(line);

camera.position.z = 2;
camera.position.y = 2;

function create_green_mesh_cube(x,y,z) {
    const geometry = new THREE.BoxGeometry( x, y, z );
    const material= new THREE.MeshBasicMaterial( { color: 0x00ff00, wireframe: true } );
    return new THREE.Mesh( geometry, material );
}

function rotate_shapes() {
    const x_rotation_delta = 0.001;
    const y_rotation_delta = 0.001;
    const z_rotation_delta = 0.001;
    for (let shape in rotating_shapes) {
        rotating_shapes[shape].rotation.x += x_rotation_delta;
        rotating_shapes[shape].rotation.y += y_rotation_delta;
        rotating_shapes[shape].rotation.z += z_rotation_delta;
    }
}
function update_info_label(message) {
    const info = document.getElementById("info");
    info.innerText = message;
}
function animate() {
    rotate_shapes();
    camera.position.z += 0.01;
    renderer.render( scene, camera );
    update_info_label(`Current camera z-value: ${camera.position.z}`);
}
renderer.setAnimationLoop(animate);
